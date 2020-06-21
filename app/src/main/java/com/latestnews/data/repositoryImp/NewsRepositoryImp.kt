package com.latestnews.data.repositoryImp

import android.annotation.SuppressLint
import com.domain.entity.ListNewsResponse
import com.domain.entity.NewsResponseVo

import com.domain.repository.NewsRepository
import com.latestnews.api.ApiConstant.API_KEY
import com.latestnews.api.ApiConstant.COUNTRY

import com.latestnews.api.api.NewsAPI
import com.latestnews.base.BaseMapFunction
import com.latestnews.data.roomdb.DiskCache
import com.latestnews.data.roomdb.entity.NewsEntity
import com.latestnews.utility.AppUtils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsAPI: NewsAPI,private val diskCache: DiskCache) : NewsRepository{

    @SuppressLint("CheckResult")
    override fun getNewsList(pageNo : Int): Observable<ListNewsResponse> {
        return getTimeFromDB().flatMap { newsList ->

            if (newsList.isNotEmpty() && AppUtils.timeDifference(  // check 2 hours time and then data delete into db
                    AppUtils.getCurrentTime(),
                    newsList[0].time
                ) >= 2
            ) {
                deleteNewsDiskdata()
                newsAPI.getNews(COUNTRY, 10, pageNo, API_KEY) // get data from server
                    .map(object : BaseMapFunction<ArrayList<NewsResponseVo>, ListNewsResponse>(
                        ListNewsResponse()
                    ) {})
                    .flatMap {
                        saveNewsDataIntoDB(it, pageNo)             //save data into db
                        return@flatMap getNewsList(it)
                    }
            } else {
                checkPageNumberIntoDB(pageNo).flatMap { dbResponse ->
                    if (dbResponse.isNotEmpty()) {
                        return@flatMap getNewsListFromDB(dbResponse)
                    } else {
                        newsAPI.getNews(COUNTRY, 10, pageNo, API_KEY)  // get data from server
                            .map(object :
                                BaseMapFunction<ArrayList<NewsResponseVo>, ListNewsResponse>(
                                    ListNewsResponse()
                                ) {})
                            .flatMap {
                                saveNewsDataIntoDB(it, pageNo)      //save data into db
                                return@flatMap getNewsList(it)
                            }
                    }
                }
            }
        }
    }

    private fun getNewsList(its: ListNewsResponse): Observable<ListNewsResponse> =
        Observable.create<ListNewsResponse>{
            it.onNext(its)
            it.onComplete()
        }


    fun getNewsListFromDB(dbResponse: List<NewsEntity>): Observable<ListNewsResponse>{
        var list = ArrayList<NewsResponseVo>()
        for (dbData in dbResponse){
            var newsResponseVo = NewsResponseVo()
            var source = newsResponseVo.Source()
            source.name = dbData.source
            newsResponseVo.source = source
            newsResponseVo.author = dbData.author
            newsResponseVo.title = dbData.title
            newsResponseVo.description = dbData.description
            newsResponseVo.url = dbData.url
            newsResponseVo.urlToImage = dbData.urlToImage
            newsResponseVo.publishedAt = dbData.publishedAt
            newsResponseVo.content = dbData.content
            list.add(newsResponseVo)
        }

        var listNewsResponse = ListNewsResponse()
        listNewsResponse.articlesList = list

        return Observable.create<ListNewsResponse> { emitter ->
            emitter.onNext(listNewsResponse)
            emitter.onComplete()
        }
    }

    fun checkPageNumberIntoDB(pageNo: Int) : Observable<List<NewsEntity>> {

       return Observable.create<List<NewsEntity>> {emitter ->
           emitter.onNext(diskCache.getCurrentPage(pageNo))
           emitter.onComplete()
       }
    }

    fun getTimeFromDB() : Observable<List<NewsEntity>> {
       return Observable.create<List<NewsEntity>> {emitter ->
           emitter.onNext(diskCache.getAll())
           emitter.onComplete()
       }
    }

    @SuppressLint("CheckResult")
    private fun saveNewsDataIntoDB(
        listNewsRes: ListNewsResponse,
        pageNo: Int
    )  {
        Observable.create<ListNewsResponse> {
            for (listRes in listNewsRes.articlesList) {
                val newsEntity = NewsEntity()
                newsEntity.source = listRes.source.name
                newsEntity.author = listRes.author
                newsEntity.title = listRes.title
                newsEntity.description = listRes.description
                newsEntity.url = listRes.url
                newsEntity.urlToImage = listRes.urlToImage
                newsEntity.publishedAt = listRes.publishedAt
                newsEntity.content = listRes.content
                newsEntity.time = AppUtils.getCurrentTime()
                newsEntity.pageNumber = pageNo

                diskCache.insertAll(newsEntity)
            }
            it.onComplete()
        }.subscribeOn(Schedulers.io())
            .subscribe { }
    }

    @SuppressLint("CheckResult")
    private fun deleteNewsDiskdata() {
        Observable.create<Boolean> { emitter ->

            diskCache.nukeTable()
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .subscribe { }
    }
}
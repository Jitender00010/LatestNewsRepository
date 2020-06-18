package com.latestnews.data.repositoryImp

import android.annotation.SuppressLint
import com.domain.entity.ListNewsResponse
import com.domain.entity.NewsResponseVo

import com.domain.enum.AppStateKey
import com.domain.repository.NewsRepository
import com.google.gson.Gson

import com.latestnews.api.api.NewsAPI
import com.latestnews.base.BaseMapFunction
import com.latestnews.data.roomdb.DiskCache
import com.latestnews.utility.AppUtils
import com.latestnews.utility.Failure
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsAPI: NewsAPI,private val diskCache: DiskCache) : NewsRepository{

    @SuppressLint("CheckResult")
    override fun getNewsList(): Observable<ListNewsResponse> {

        return checkNewsData().flatMap { isValid ->
            if (isValid){
              return@flatMap  getNewsDataFromDisk()
            }else{
                return@flatMap newsAPI.getNews("us","bab70fdc508a46aeb197ced873ade2b5").map (object : BaseMapFunction<List<NewsResponseVo>, ListNewsResponse>(ListNewsResponse()){})
                    .flatMap { saveNewsDataFromDisk(it) }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun saveNewsDataFromDisk(listNewsRes : ListNewsResponse) : Observable<ListNewsResponse> =
        Observable.create {
            diskCache.put(AppStateKey.NEWS_DATA.name, listNewsRes)
            it.onNext(listNewsRes)
            it.onComplete()
    }

    private fun getNewsDataFromDisk() : Observable<ListNewsResponse> {

     return Observable.create {
            val appConfigResponse = diskCache.get(AppStateKey.NEWS_DATA.name)
            appConfigResponse?.let { its ->
                val menuFromServer = Gson().fromJson(appConfigResponse, ListNewsResponse::class.java)
                it.onNext(menuFromServer)
            }
            it.onComplete()
        }
    }

    private fun checkNewsData() : Observable<Boolean> =
        Observable.create {
            val newsResponse = diskCache.get(AppStateKey.NEWS_DATA.name)
            val timeResponse  = diskCache.get(AppStateKey.LAST_TIME.name)

            val lastTime = Gson().fromJson(timeResponse, String::class.java)

            if (lastTime == null && newsResponse == null ){
                it.onNext(false)
                saveTime(AppUtils.getCurrentTime())
                deleteNewsDiskdata()
            }
            else if (newsResponse == null){
                it.onNext(false)
            }
            else{
                if (AppUtils.timeDifference(lastTime,AppUtils.getCurrentTime()) >=2 ) {
                    it.onNext(false)
                    saveTime(AppUtils.getCurrentTime())
                    deleteNewsDiskdata()

                }else{
                    it.onNext(true)
                }
            }
            it.onComplete()
        }


    @SuppressLint("CheckResult")
    private fun saveTime(time : String) {
            Observable.create<Any> { emitter ->
                diskCache.put(AppStateKey.LAST_TIME.name, time)
                emitter.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .subscribe { }
    }

    @SuppressLint("CheckResult")
    private fun deleteNewsDiskdata() {
            Observable.create<Boolean> { emitter ->
                diskCache.remove<Boolean>(AppStateKey.NEWS_DATA.name)
                emitter.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .subscribe { }
    }
}
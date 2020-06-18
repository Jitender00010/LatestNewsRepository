package com.latestnews.data.repositoryImp

import android.annotation.SuppressLint
import com.domain.entity.ListNewsResponse

import com.domain.enum.AppStateKey
import com.domain.repository.NewsRepository
import com.google.gson.Gson
import com.latestnews.api.BaseResponse
import com.latestnews.api.api.NewsAPI
import com.latestnews.base.BaseMapFunction
import com.latestnews.data.roomdb.DiskCache
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsAPI: NewsAPI,private val diskCache: DiskCache) : NewsRepository{

    override fun getNewsList(): Observable<ListNewsResponse> {

       /*return newsAPI.getNews("us","bab70fdc508a46aeb197ced873ade2b5").map (object : BaseMapFunction<List<NewsResponseVO>, ListNewsResponse>(ListNewsResponse()){})
            .flatMap { saveNewsDataFromDisk(it) }
*/
        return getNewsDataFromDisk()
    }

    @SuppressLint("CheckResult")
    private fun saveNewsDataFromDisk(listNewsRes : ListNewsResponse) : Observable<ListNewsResponse>{
       return Observable.create {
            diskCache.put(AppStateKey.NEWS_DATA.name, listNewsRes)
            it.onNext(listNewsRes)
            it.onComplete()
        }
    }

    private fun getNewsDataFromDisk() : Observable<ListNewsResponse> =
        Observable.create {
            val appConfigResponse = diskCache.get(AppStateKey.NEWS_DATA.name)
            val menuFromServer = Gson().fromJson(appConfigResponse, ListNewsResponse::class.java)
            it.onNext(menuFromServer)
            it.onComplete()
        }
}
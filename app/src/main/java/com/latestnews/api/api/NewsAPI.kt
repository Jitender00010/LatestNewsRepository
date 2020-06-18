package com.latestnews.api.api

import com.domain.entity.NewsResponseVO
import io.reactivex.Observable
import com.latestnews.api.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI{

    @GET("top-headlines")
    fun getNews(@Query("country") country : String,@Query("apiKey") key:String): Observable<BaseResponse<NewsResponseVO>>
}
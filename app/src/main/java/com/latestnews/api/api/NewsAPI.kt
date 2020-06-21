package com.latestnews.api.api

import com.domain.entity.NewsResponseVo
import io.reactivex.Observable
import com.latestnews.api.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI{

    @GET("top-headlines")
    fun getNews(@Query("country") country : String,@Query("pageSize") pageSize : Int,
                @Query("page") page : Int, @Query("apiKey") key:String)
            : Observable<BaseResponse<ArrayList<NewsResponseVo>>>
}
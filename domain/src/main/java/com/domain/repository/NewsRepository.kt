package com.domain.repository
import com.domain.entity.NewsResponseVO
import io.reactivex.Observable

interface NewsRepository {

    fun getNewsList() : Observable<NewsResponseVO>
}
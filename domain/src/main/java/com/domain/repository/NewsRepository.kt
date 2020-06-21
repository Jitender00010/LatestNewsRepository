package com.domain.repository
import com.domain.entity.ListNewsResponse
import io.reactivex.Observable

interface NewsRepository {

    fun getNewsList(page : Int): Observable<ListNewsResponse>
}
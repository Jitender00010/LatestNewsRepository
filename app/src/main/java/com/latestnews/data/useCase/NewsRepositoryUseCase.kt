package com.latestnews.data.useCase

import com.domain.entity.ListNewsResponse
import com.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryUseCase @Inject constructor(val newsRepository: NewsRepository) {

    fun getNewsList(page : Int) : Observable<ListNewsResponse>
    {
        return newsRepository.getNewsList(page)
    }
}
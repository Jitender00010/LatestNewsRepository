package com.latestnews.data.useCase

import com.domain.entity.NewsResponseVO
import com.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryUseCase @Inject constructor(val newsRepository: NewsRepository) {

    fun getNewsList() : Observable<NewsResponseVO>
    {
        return newsRepository.getNewsList()
    }
}
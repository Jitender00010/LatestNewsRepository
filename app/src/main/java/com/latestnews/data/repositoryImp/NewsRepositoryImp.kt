package com.latestnews.data.repositoryImp

import com.domain.entity.NewsResponseVO
import com.domain.repository.NewsRepository
import com.latestnews.api.api.NewsAPI
import com.latestnews.base.BaseMapFunction
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsAPI: NewsAPI) : NewsRepository{
    override fun getNewsList(): Observable<NewsResponseVO> {
        return newsAPI.getNews("us","bab70fdc508a46aeb197ced873ade2b5").map (object : BaseMapFunction<NewsResponseVO, NewsResponseVO>(NewsResponseVO(null,null,null,null,null,null,null,null)){})
    }
}
package com.latestnews.data.roomdb

import com.latestnews.data.roomdb.entity.NewsEntity

interface Cache {
    fun getAll(): List<NewsEntity>

    fun countUsers(): Integer

    fun getCurrentPage(pageNo : Int ): List<NewsEntity>

    fun insertAll(newsEntity: NewsEntity)

    fun delete(user: NewsEntity)

    fun nukeTable()
}

interface DiskCache : Cache
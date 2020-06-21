package com.latestnews.data.roomdb

import com.callmanagerfinal.data.roomdb.StringDB
import com.google.gson.Gson
import com.latestnews.data.roomdb.entity.NewsEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All keys are maintained at AppStateKey, but not used directly as a dependency.
 *
 * @author Jitender
 */
@Singleton
class DiskCacheImpl @Inject constructor(
    private val stringDB: StringDB,
    private val gson: Gson
) : DiskCache {
    private val newsDAO = stringDB.newsDao()

    override fun getCurrentPage(pageNo : Int ): List<NewsEntity> {
        return newsDAO.getCurrentPage(pageNo)
    }


    override fun getAll(): List<NewsEntity> {
        return newsDAO.getAll()
    }

    override fun countUsers(): Integer {
        return newsDAO.countUsers()
    }

    override fun insertAll(newsEntity: NewsEntity) {
        return newsDAO.insertAll(newsEntity)
    }

    override fun delete(user: NewsEntity) {
        return newsDAO.delete(user)
    }

    override fun nukeTable() {
        return newsDAO.nukeTable()
    }
}

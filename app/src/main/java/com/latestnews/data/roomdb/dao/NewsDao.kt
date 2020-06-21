package com.latestnews.data.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.latestnews.data.roomdb.entity.NewsEntity
import io.reactivex.Observable

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsTable")
    fun getAll(): List<NewsEntity>

    @Query("SELECT * FROM NewsTable WHERE pageNumber = :pageNo")
    fun getCurrentPage(pageNo : Int): List<NewsEntity>

    @Query("SELECT COUNT(*) from NewsTable")
    fun countUsers(): Integer

    @Insert
    fun insertAll(newsEntity: NewsEntity)

    @Delete
    fun delete(user: NewsEntity)

    @Query("DELETE FROM NewsTable")
    fun nukeTable()
}
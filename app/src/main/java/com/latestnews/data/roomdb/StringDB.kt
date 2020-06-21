package com.callmanagerfinal.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latestnews.data.roomdb.dao.NewsDao
import com.latestnews.data.roomdb.entity.NewsEntity

/**
 * @author Jitender
 */
@Database(
        entities = [
            NewsEntity::class
        ],
        version = 2
)
abstract class StringDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
package com.callmanagerfinal.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.callmanagerfinal.data.roomdb.dao.StringDao
import com.callmanagerfinal.data.roomdb.entity.StringEntity

/**
 * @author Jitender
 */
@Database(
        entities = [
            StringEntity::class
        ],
        version = 1
)
abstract class StringDB : RoomDatabase() {

    abstract fun stringDao(): StringDao
}
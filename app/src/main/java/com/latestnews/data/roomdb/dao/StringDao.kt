package com.latestnews.data.roomdb.dao

import androidx.room.*
import com.latestnews.data.roomdb.entity.StringEntity

/**
 * @author Jitender
 */
@Dao
interface StringDao {

    @Query("SELECT * FROM StringEntity WHERE queryKey = :queryKey")
    fun getValue(queryKey: String): StringEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putValue(stringEntity: StringEntity)

    @Delete
    fun delete(stringEntity: StringEntity)
}
package com.latestnews.data.roomdb

import android.util.Log
import com.callmanagerfinal.data.roomdb.StringDB
import com.google.gson.Gson
import com.latestnews.data.roomdb.entity.StringEntity
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

    private val stringDao = stringDB.stringDao()

    override fun get(key: String): String = String().let {
        try {
            val stringEntity = stringDao.getValue(key)

            stringEntity.toString()
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    //todo implement like hashmap put. return object instead of json
    override fun <V : Any> put(key: String, value: V): Boolean {
        return try {
            stringDao.putValue(
                StringEntity(
                    key,
                    gson.toJson(value)
                )
            )
            true
        } catch (e: Exception) {
            Log.e("Error ", " DiskCacheImp" + e.message.toString())
            false
        }
    }

    override fun <V : Any> remove(key: String): Boolean {
        return try {
            stringDao.delete(StringEntity(key,""))
            true
        } catch (e: Exception) {
            Log.e("Error ", " DiskCacheImp" + e.message.toString())
            false
        }
}
    override fun evict(): Boolean {
        return try {
            stringDB.clearAllTables()

            true
        } catch (e: Exception) {
            Log.e("Error ", " DiskCacheImp" + e.message.toString())
            false
        }
    }
}

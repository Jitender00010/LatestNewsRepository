package com.latestnews.data.roomdb

interface Cache {

    fun get(key: String): String?

    fun <V : Any> put(key: String, value: V): Boolean

    fun <V : Any> remove(key: String): Boolean

    fun evict():Boolean
}

interface DiskCache : Cache
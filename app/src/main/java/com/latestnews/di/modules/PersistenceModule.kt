package com.latestnews.di.modules


import android.content.Context
import androidx.room.Room
import com.callmanagerfinal.data.roomdb.StringDB
import com.google.gson.Gson
import com.latestnews.data.roomdb.DiskCache
import com.latestnews.data.roomdb.DiskCacheImpl
import com.latestnews.di.qualifier.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideTestDB(@Application context: Context): StringDB =
        Room.databaseBuilder(
            context,
            StringDB::class.java,
            StringDB::class.java.name
        )
            .build()

    @Provides
    @Singleton
    fun provideDiskCache(diskCacheImpl: DiskCacheImpl): DiskCache = diskCacheImpl
}
package com.callmanagerfinal.di.modules


import android.content.Context
import androidx.room.Room
import com.callmanagerfinal.data.roomdb.DiskCache
import com.callmanagerfinal.data.roomdb.DiskCacheImpl
import com.callmanagerfinal.data.roomdb.StringDB
import com.callmanagerfinal.di.qualifier.Application
import com.google.gson.Gson
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
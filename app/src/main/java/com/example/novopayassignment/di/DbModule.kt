package com.example.novopayassignment.di

import android.app.Application
import androidx.room.Room
import com.example.novopayassignment.room.AppDB
import com.example.novopayassignment.room.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DBModule(mApplication: Application) {
    private val newsDB: AppDB = Room.databaseBuilder(mApplication, AppDB::class.java, "News.db").build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDB {
        return newsDB
    }


    @Singleton
    @Provides
    internal fun providesProductDao(newsDB: AppDB): NewsDao {
        return newsDB.newsDao()
    }
}
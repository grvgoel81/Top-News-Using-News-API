package com.example.novopayassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.novopayassignment.model.DataConverter
import com.example.novopayassignment.model.NewsData

@TypeConverters(DataConverter::class)
@Database(entities = [NewsData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
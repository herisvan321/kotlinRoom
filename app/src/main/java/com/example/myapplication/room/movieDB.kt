package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(movie::class), version = 1)
abstract class movieDB : RoomDatabase() {
    abstract fun movieDao(): movieDao
    companion object{

       @Volatile
       private var instance : movieDB? = null
       private val LOCK = Any()

       operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
           instance ?: buildDatabase(context).also {
               instance = it
           }
       }

       private fun buildDatabase(context:Context) = Room.databaseBuilder(
           context.applicationContext,
           movieDB::class.java, "movie.db "
       ).build()


    }
}
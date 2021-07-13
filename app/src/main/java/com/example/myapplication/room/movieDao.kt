package com.example.myapplication.room

import androidx.room.*

@Dao
interface movieDao {
    @Insert
    suspend fun addMovies(movie : movie)

    @Update
    suspend fun updateMovies(movie : movie)

    @Delete
    suspend fun deleteMovies(movie : movie)

    @Query("SELECT * FROM movie")
    suspend fun getMovies() : List<movie>
}
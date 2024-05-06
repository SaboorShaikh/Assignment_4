package com.example.assignment_4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import android.database.Cursor
import androidx.lifecycle.LiveData

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokes")
    fun getAll(): LiveData<List<Joke>>

    @Query("SELECT * FROM jokes")
    fun getAllJokesCursor(): Cursor

    @Query("SELECT * FROM jokes WHERE id = :id")
    fun getJokeCursorById(id: Long): Cursor

    @Insert
    fun insert(joke: Joke)

    @Update
    fun update(joke: Joke): Int

    @Query("DELETE FROM jokes WHERE id = :id")
    fun deleteJokeById(id: Long): Int

    // Add more operations as needed
}

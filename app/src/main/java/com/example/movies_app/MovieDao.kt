package com.example.movies_app

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    @Update
    fun update(note:Note)
    @Delete
    fun delete(note:Note)

    @Query("DELETE FROM offline_data")
    fun DeleteAll()

    @Query("SELECT * FROM offline_data ORDER BY priority DESC")
    fun getData():LiveData<List<Note>>

}
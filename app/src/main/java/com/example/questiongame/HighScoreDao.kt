package com.example.questiongame

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HighScoreDao {

    @Insert
    fun insert(item: HighScore)

    @Delete
    fun delete(item: HighScore)


    @Query("SELECT * FROM highScore_table")
    fun getAll(): List<HighScore>

    @Query("SELECT * FROM highScore_table WHERE category LIKE :categoryName ")
    fun findByCategory(categoryName: String): List<HighScore>


}
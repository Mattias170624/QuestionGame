package com.example.questiongame

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HighScore::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun highScoreDao(): HighScoreDao
}
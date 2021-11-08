package com.example.questiongame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highscore_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "score") var score: String?,
    @ColumnInfo(name = "difficulty") var difficulty: String?,
    @ColumnInfo(name = "category") var category: String?
)
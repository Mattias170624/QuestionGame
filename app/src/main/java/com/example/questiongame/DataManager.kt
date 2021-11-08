package com.example.questiongame

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

object DataManager { // Saved user data throughout all activities

    var subject: Any = 0
    var difficulty: Int = 0
    var number1: Int = 0
    var number2: Int = 0
    var sum: Int = 0
    var points: Int = 0
    var failedAttempts: Int = 0
    var passedAttempts: Int = 0
    var questions: Int = 0


    fun difficultyProperties() {
        when (difficulty) {
            1 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (10..40).random()
                    number2 = (10..40).random()
                } else if (subject == "*") {
                    number1 = (2..6).random()
                    number2 = (2..6).random()
                } else {
                    number1 = ((2..(20) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            2 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (50..130).random()
                    number2 = (50..130).random()
                } else if (subject == "*") {
                    number1 = (3..9).random()
                    number2 = (3..9).random()
                } else {
                    number1 = ((4..(60) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            3 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (200..500).random()
                    number2 = (200..500).random()
                } else if (subject == "*") {
                    number1 = (7..15).random()
                    number2 = (7..15).random()
                } else {
                    number1 = ((4..(90) / 2).random() * 2) // Even number
                    primeNumberMaker()
                }
            }
        }
    }

    fun subjectProperties() {
        when (subject) {
            1 -> subject = "+"
            2 -> subject = "-"
            3 -> subject = "*"
            4 -> subject = "/"
        }
    }

    private fun primeNumberMaker() { // Random primenumber of number1 = value of number2
        val listNum = mutableListOf<Int>()
        for (num in 2..number1 / 2) {
            if (number1 % num == 0) {
                listNum.add(num)
            }
            number2 = listNum.random()
        }
    }

    fun wipeData() { // Sets userdata to 0
        passedAttempts = 0
        failedAttempts = 0
        questions = 0
        points = 0

        Log.d("!", "$failedAttempts $questions $points")
    }

}












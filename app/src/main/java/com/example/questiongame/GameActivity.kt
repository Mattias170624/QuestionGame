package com.example.questiongame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.questiongame.DataManager.difficulty
import com.example.questiongame.DataManager.subject

open class GameActivity : AppCompatActivity() {

    var number1: Int = 0
    var number2: Int = 0
    var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val questionTextView: TextView = findViewById(R.id.questionTextView)
        val answerEditText: EditText = findViewById(R.id.answerEditText)
        val button = findViewById<Button>(R.id.button)


        button.setOnClickListener() {
            button.text = "Submit!"

            questionTextView.text = questionGenerator()
        }
    }

    open fun subjectProperties() {
        when (subject) {
            1 -> {
                subject = "+"
                sum = (number1.plus(number2))
            }
            2 -> {
                subject = "-"
                sum = (number1.minus(number2))
            }
            3 -> {
                subject = "*"
                sum = (number1.times(number2))
            }
            4 -> {
                subject = "/"
                sum = (number1.div(number2))
            }
        }
    }

    open fun difficultyProperties() {
        when (difficulty) {
            1 -> {
                if (subject == "+" || subject == "-") {
                    var sum = (number1.plus(number2))
                    number1 = (50..100).random()
                    number2 = (50..100).random()
                } else if (subject == "*") {
                    number1 = (3..9).random()
                    number2 = (3..9).random()
                } else {
                    number1 = ((4..(40) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            2 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (100..200).random()
                    number2 = (100..200).random()
                } else if (subject == "*") {
                    number1 = (5..12).random()
                    number2 = (5..12).random()
                } else {
                    number1 = ((4..(60) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            3 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (300..500).random()
                    number2 = (300..500).random()
                } else if (subject == "*") {
                    number1 = (9..19).random()
                    number2 = (9..19).random()
                } else {
                    number1 = ((4..(90) / 2).random() * 2)
                    primeNumberMaker()
                }
            }
        }
    }

    private fun primeNumberMaker() {
        val listNum = mutableListOf<Int>()
        for (num in 2..number1 / 2) {
            if (number1 % num == 0) {
                listNum.add(num)
                number2 = listNum.random()
            }
        }
    }

    private fun questionGenerator(): String {
        subjectProperties()
        difficultyProperties()

        return "$number1 $subject $number2"
    }
}




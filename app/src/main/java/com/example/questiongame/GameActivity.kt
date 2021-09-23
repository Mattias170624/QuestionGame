package com.example.questiongame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.questiongame.DataManager.difficulty
import com.example.questiongame.DataManager.subject

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var questionTextView: TextView = findViewById(R.id.questionTextView)
        var answerEditText = findViewById<EditText>(R.id.answerEditText)
        var button = findViewById<Button>(R.id.button)


        button.setOnClickListener() {
            Log.d("!!!", "Start button pressed")
            questionTextView.text = questionGenerator()
            button.text = "Submit!"
        }
    }

    private fun questionGenerator(): String {
        var number1: Int = 0
        var number2: Int = 0

        when (subject) {
            1 -> subject = "+"
            2 -> subject = "-"
            3 -> subject = "*"
            4 -> subject = "/"
        }

        when (difficulty) {

            1 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (50..100).random()
                    number2 = (50..100).random()
                } else if (subject == "*") {
                    number1 = (3..9).random()
                    number2 = (3..9).random()
                } else {
                    number1 = ((2..(30) / 2).random() * 2) // number1 gets assigned an even number
                    val listNum = mutableListOf<Int>()
                    for (num in 2..number1 / 2) {
                        if (number1 % num == 0) { // Primenumber check for number2
                            listNum.add(num) // Adds primenumber to numList
                            number2 = listNum.random()
                        }
                    }
                }
            }

            2 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (100..200).random()
                    number2 = (100..200).random()
                } else if (subject == "*") {
                    number1 = (4..15).random()
                    number2 = (4..15).random()
                } else {
                    number1 = ((4..(60) / 2).random() * 2) // number1 gets assigned an even number
                    val listNum = mutableListOf<Int>()
                    for (num in 4..number1 / 2) {
                        if (number1 % num == 0) { // Primenumber check for number2
                            listNum.add(num) // Adds primenumber to numList
                            number2 = listNum.random()
                        }
                    }
                }
            }

            3 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (300..600).random()
                    number2 = (300..600).random()
                } else if (subject == "*") {
                    number1 = (7..19).random()
                    number2 = (7..19).random()
                } else {
                    number1 = ((8..(90) / 2).random() * 2) // number1 gets assigned an even number
                    val listNum = mutableListOf<Int>()
                    for (num in 4..number1 / 2) {
                        if (number1 % num == 0) { // Primenumber check for number2
                            listNum.add(num) // Adds primenumber to numList
                            number2 = listNum.random()
                        }
                    }
                }
            }
        }
        return "$number1 $subject $number2"
    }
}



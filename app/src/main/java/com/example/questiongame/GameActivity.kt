package com.example.questiongame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.questiongame.DataManager.number1
import com.example.questiongame.DataManager.number2
import com.example.questiongame.DataManager.points
import com.example.questiongame.DataManager.subject
import com.example.questiongame.DataManager.sum
import com.example.questiongame.R.id.questionTextView
import com.example.questiongame.R.id.userInputEditText

open class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val questionTextView: TextView = findViewById(questionTextView)
        val userInput: EditText = findViewById(userInputEditText)
        val button = findViewById<Button>(R.id.button)

        val duration = Toast.LENGTH_SHORT
        val wrongText = "Wrong answer!"
        val toast = Toast.makeText(applicationContext, wrongText, duration)
        toast.setGravity(1, -225, 70) // Popup text location
        userInput.requestFocus() // Keyboard focus on start

        DataManager.subjectProperties()
        questionTextView.text = questionGenerator()

        button.setOnClickListener() {
            answerChecker()
            when (userInput.text.toString().contains("$sum")) {
                true -> {
                    questionTextView.text = questionGenerator()
                    userInput.setText("")
                    points++
                }
                false -> {
                    userInput.setText("")
                    toast.show()
                    points--
                }
            }
            Log.d("!","Total points: $points")
        }
    }

    open fun questionGenerator(): String {
        DataManager.difficultyProperties()

        return "$number1 $subject $number2"
    }

    open fun sumOfNumbers(): Int {
        when (subject) {
            "+" -> sum = (number1.plus(number2))
            "-" -> sum = (number1.minus(number2))
            "*" -> sum = (number1.times(number2))
            "/" -> {
                Log.d("!","xxxxxxxx$number1 $number2 $sum")
                sum = (number1.div(number2))
            }
        }
        return sum
    }

    open fun answerChecker() {
        sumOfNumbers()
    }
}




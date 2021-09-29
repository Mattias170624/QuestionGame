package com.example.questiongame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.questiongame.R.id.*
import java.util.*

open class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val questionTextView: TextView = findViewById(questionTextView)
        val userInput: EditText = findViewById(userInputEditText)
        val button: Button = findViewById(R.id.button)
        val clock: TextView = findViewById(clockTextView)
        val pointsCounter: TextView = findViewById(pointCounterTextView)

        val duration = Toast.LENGTH_SHORT
        val wrongText = "Wrong answer!"
        val toast = Toast.makeText(applicationContext, wrongText, duration)
        val gameTime: CountDownTimer
        toast.setGravity(1, -225, 70) // Popup text location
        userInput.requestFocus() // Keyboard focus on start
        clock.text.toString()

        DataManager.subjectProperties()
        questionTextView.text = questionGenerator()

        gameTime = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                clock.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                startEndScreenActivity()
            }
        }

        gameTime.start()
        points = 0 // Resets point value if user wants to change subject / difficulty

        button.setOnClickListener() {
            sumOfNumbers()
            pointsCounter.text = "$points"
            when (userInput.text.toString().contains("$sum")) {
                true -> {
                    points++
                    userInput.setText("")
                    questionTextView.text = questionGenerator()
                    if (points > 0) pointsCounter.setTextColor(Color.parseColor("#90ee90")) // Points color = green
                    pointsCounter.text = "$points"
                }
                false -> {
                    points--
                    userInput.setText("")
                    toast.show()
                    if (points <= 0) pointsCounter.setTextColor(Color.parseColor("#FF0000")) // Points color = red
                    pointsCounter.text = "$points"
                }
            }
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
            "/" -> sum = (number1.div(number2))
        }
        return sum
    }

    open fun startEndScreenActivity() {
        val intent = Intent(this@GameActivity, EndScreenActivity::class.java)
        startActivity(intent)
    }
}




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

open class GameActivity : AppCompatActivity() {

    lateinit var questionTextView: TextView
    lateinit var pointsCounter: TextView
    lateinit var clock: TextView
    lateinit var userInput: EditText
    lateinit var button: Button
    lateinit var gameTime: CountDownTimer
    lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        questionTextView = findViewById(R.id.questionTextView)
        pointsCounter = findViewById(R.id.pointCounterTextView)
        clock = findViewById(R.id.clockTextView)
        userInput = findViewById(R.id.userInputEditText)
        button = findViewById(R.id.button)
        toast = Toast(this)

        gameTime = object : CountDownTimer(31000, 1000) { // Game time starts
            override fun onTick(millisUntilFinished: Long) {
                clock.text = "${millisUntilFinished / 1000}" // Converts ms to sec
            }

            override fun onFinish() {
                startEndScreenActivity()
            }
        }

        pointsCounter.text = "${DataManager.points}"
        questionTextView.text = questionGenerator()
        gameTime.start()
        sumOfNumbers()

        button.setOnClickListener {
            DataManager.questions++
            sumOfNumbers()
            when (userInput.text.toString() == (DataManager.sum.toString())) {
                true -> {
                    DataManager.passedAttempts++
                    DataManager.points++
                    userInput.setText("")
                    questionTextView.text = questionGenerator()
                    if (DataManager.points > 0) pointsCounter.setTextColor(Color.parseColor("#90ee90")) // Points color = green
                    pointsCounter.text = "${DataManager.points}"
                }
                false -> {
                    DataManager.points--
                    DataManager.failedAttempts++
                    userInput.setText("")
                    toastTextRandomizer()
                    if (DataManager.points <= 0) pointsCounter.setTextColor(Color.parseColor("#FF0000")) // Points color = red
                    pointsCounter.text = "${DataManager.points}"
                }
            }
        }
    }

    open fun questionGenerator(): String {
        DataManager.subjectProperties()
        DataManager.difficultyProperties()

        return "${DataManager.number1} ${DataManager.subject} ${DataManager.number2}"
    }

    open fun sumOfNumbers(): Int {
        when (DataManager.subject) {
            "+" -> DataManager.sum = (DataManager.number1.plus(DataManager.number2))
            "-" -> DataManager.sum = (DataManager.number1.minus(DataManager.number2))
            "*" -> DataManager.sum = (DataManager.number1.times(DataManager.number2))
            "/" -> DataManager.sum = (DataManager.number1.div(DataManager.number2))
        }
        return DataManager.sum
    }

    open fun toastTextRandomizer() {
        val toastList = mutableListOf<String>()
        toastList.add("Terrible..")
        toastList.add("Wow so bad")
        toastList.add("Totally wrong!")
        toastList.add("Very bad!")
        toastList.add("Damn.. ")
        toastList.add("Really bad")

        toast = Toast.makeText(applicationContext, toastList.random(), Toast.LENGTH_SHORT)
        toast.setGravity(0, 0, -200) // Position for toast on screen
        toast.show()
    }

    open fun startEndScreenActivity() {
        val intent = Intent(this@GameActivity, EndScreenActivity::class.java)
        startActivity(intent)
        toast.cancel()
    }
}




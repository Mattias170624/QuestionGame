package com.example.questiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt

class EndScreenActivity : AppCompatActivity() {

    lateinit var resultText: TextView
    lateinit var goodOrBadText: TextView
    lateinit var pointResult: TextView
    lateinit var subjectResult: TextView
    lateinit var difficultyResult: TextView
    lateinit var attemptsText: TextView
    lateinit var percentAttemptResult: TextView
    lateinit var playAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)

        resultText = findViewById(R.id.resultTextView)
        goodOrBadText = findViewById(R.id.headerTextView)
        pointResult = findViewById(R.id.pointResultTextView)
        subjectResult = findViewById(R.id.subjectTextView)
        difficultyResult = findViewById(R.id.difficultyTextView)
        attemptsText = findViewById(R.id.attemptsTextView)
        percentAttemptResult = findViewById(R.id.percentAttemptsTextView)
        playAgainButton = findViewById(R.id.playAgainButton)

        titleChanger() // Changes title based on point value
        resultProperties()

        playAgainButton.setOnClickListener {
            DataManager.wipeData()
            val intent = Intent(this@EndScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun percentAttemptsProperties() { // Success rate calculator
        var percentNum = (DataManager.passedAttempts).toDouble() / (DataManager.questions) * 100
        percentAttemptResult.text = "${percentNum.roundToInt()} % Correct guesses"
        Log.d("!", "Passed: ${DataManager.passedAttempts}")
        Log.d("!", "/")
        Log.d("!", "Questions: ${DataManager.questions}")
    }

    private fun resultProperties() {
        percentAttemptsProperties()
        pointResult.text = "Points: ${DataManager.points}"
        attemptsText.text = "Failed attempts: ${DataManager.failedAttempts}"

        when (DataManager.difficulty) {
            1 -> difficultyResult.text = "Difficulty: Easy"
            2 -> difficultyResult.text = "Difficulty: Medium"
            3 -> difficultyResult.text = "Difficulty: Hard"
        }

        when (DataManager.subject) {
            "+" -> subjectResult.text = "Subject: addition"
            "-" -> subjectResult.text = "Subject: Subtraction"
            "*" -> subjectResult.text = "Subject: Multiplication"
            "/" -> subjectResult.text = "Subject: Division"
        }
    }

    private fun titleChanger() {
        if (DataManager.points <= 0) {
            goodOrBadText.text = "You did decent..."
        } else goodOrBadText.text = "You did great!"
    }
}

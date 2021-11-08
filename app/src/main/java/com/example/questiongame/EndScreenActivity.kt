package com.example.questiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.*
import kotlin.math.roundToInt

class EndScreenActivity : AppCompatActivity() {

    lateinit var db: AppDatabase

    lateinit var resultText: TextView
    lateinit var goodOrBadText: TextView
    lateinit var pointResult: TextView
    lateinit var subjectResult: TextView
    lateinit var difficultyResult: TextView
    lateinit var attemptsText: TextView
    lateinit var percentAttemptResult: TextView
    lateinit var playAgainButton: Button
    lateinit var myHighScore: Button
    lateinit var addHighScore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "high-score-list")
            .fallbackToDestructiveMigration()
            .build()

        resultText = findViewById(R.id.resultTextView)
        goodOrBadText = findViewById(R.id.headerTextView)
        pointResult = findViewById(R.id.pointResultTextView)
        subjectResult = findViewById(R.id.subjectTextView)
        difficultyResult = findViewById(R.id.difficultyTextView)
        attemptsText = findViewById(R.id.attemptsTextView)
        percentAttemptResult = findViewById(R.id.percentAttemptsTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        myHighScore = findViewById(R.id.myHighScore)
        addHighScore = findViewById(R.id.addHighScore)

        titleChanger() // Changes title based on point value
        resultProperties()
        var hasAdded = false
        playAgainButton.setOnClickListener {
            DataManager.wipeData()
            val intent = Intent(this@EndScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }

        addHighScore.setOnClickListener {

             //GlobalScope.launch { db.clearAllTables() }

            if (!hasAdded) {
                val item = HighScore(0,
                    "${DataManager.points}",
                    "${DataManager.difficulty}",
                    "${DataManager.subject}")
                saveItem(item)
                Toast.makeText(this, "New score added!", Toast.LENGTH_SHORT).show()
                hasAdded = true
            } else {
                Toast.makeText(this, "Highscore already added!", Toast.LENGTH_SHORT).show()
            }




        }

        myHighScore.setOnClickListener {
            Log.d("!", "<--------- All your recorded highscores --------->")
            GlobalScope.launch {
                val highScoreList = loadAllItems().await()
                for (score in highScoreList) {
                    Log.d("!", "$score")
                }
            }
        }


    }

    fun saveItem(highScore: HighScore) {
        GlobalScope.launch(Dispatchers.IO) {
            db.highScoreDao().insert(highScore)
        }
    }

    fun loadAllItems(): Deferred<List<HighScore>> = GlobalScope.async(Dispatchers.IO) {
        db.highScoreDao().getAll()
    }

    fun loadByCategory(category: String) =
        GlobalScope.async(Dispatchers.IO) {
            db.highScoreDao().findByCategory(category)
        }

    fun delete(highScore: HighScore) =
        GlobalScope.launch(Dispatchers.IO) {
            db.highScoreDao().delete(highScore)
        }


    private fun percentAttemptsProperties() { // Success rate calculator
        val percentNum = (DataManager.passedAttempts).toDouble() / (DataManager.questions) * 100
        if (percentNum.isNaN()) {
            percentAttemptResult.text = "0 % Correct guesses"
        } else {
            percentAttemptResult.text = "$percentNum % Correct guesses"
            Log.d("!", "Passed: ${DataManager.passedAttempts}")
            Log.d("!", "/")
            Log.d("!", "Questions: ${DataManager.questions}")
        }
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

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}

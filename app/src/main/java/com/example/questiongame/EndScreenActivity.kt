package com.example.questiongame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.questiongame.DataManager.difficulty
import com.example.questiongame.DataManager.points
import com.example.questiongame.DataManager.subject

class EndScreenActivity : AppCompatActivity() {

    lateinit var resultText: TextView
    lateinit var goodOrBadText: TextView
    lateinit var pointResult: TextView
    lateinit var subjectResult: TextView
    lateinit var difficultyResult: TextView
    lateinit var playAgainButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)

        resultText = findViewById(R.id.resultTextView)
        goodOrBadText = findViewById(R.id.headerTextView)
        pointResult = findViewById(R.id.pointResultTextView)
        subjectResult = findViewById(R.id.subjectTextView)
        difficultyResult = findViewById(R.id.difficultyTextView)
        playAgainButton = findViewById(R.id.playAgainButton)

        headerChanger()
        resultProperties()

        playAgainButton.setOnClickListener {
            val intent = Intent(this@EndScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun resultProperties() {
        pointResult.text = "$points"

        when (difficulty) {
            1 -> {
                difficultyResult.text = "Easy"
                difficultyResult.setTextColor(Color.parseColor("#90ee90"))
            }
            2 -> {
                difficultyResult.text = "Medium"
                difficultyResult.setTextColor(Color.parseColor("#FFA500"))
            }
            3 -> {
                difficultyResult.text = "Hard"
                difficultyResult.setTextColor(Color.parseColor("#FF0000"))
            }
        }

        when (subject) {
            "+" -> subjectResult.text = "Addition"
            "-" -> subjectResult.text = "Subtraction"
            "*" -> subjectResult.text = "Multiplication"
            "/" -> subjectResult.text = "Division"
        }
    }

    private fun headerChanger() {
        if (points <= 0) {
            goodOrBadText.text = "You did decent..."
        } else goodOrBadText.text = "You did great!"
    }

}

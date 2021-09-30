package com.example.questiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

open class DifficultyActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var buttonStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        buttonStart = findViewById(R.id.buttonStart)

        button1.setOnClickListener {
            DataManager.difficulty = 1
            showButtonStart()
        }

        button2.setOnClickListener {
            DataManager.difficulty = 2
            showButtonStart()
        }

        button3.setOnClickListener {
            DataManager.difficulty = 3
            showButtonStart()
        }
    }

    open fun showButtonStart() {
        buttonStart.visibility = View.VISIBLE
        buttonStart.setOnClickListener {
            startGameActivity()
        }
    }

    open fun startGameActivity() {
        val intent = Intent(this@DifficultyActivity, GameActivity::class.java)
        startActivity(intent)
    }
}
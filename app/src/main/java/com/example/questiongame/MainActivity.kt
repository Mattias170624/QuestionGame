package com.example.questiongame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
open class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("!",">> Started")

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)


        button1.setOnClickListener {
            DataManager.subject = 1
            startDifficultyActivity()
        }
        button2.setOnClickListener {
            DataManager.subject = 2
            startDifficultyActivity()
        }
        button3.setOnClickListener {
            DataManager.subject = 3
            startDifficultyActivity()
        }
        button4.setOnClickListener {
            DataManager.subject = 4
            startDifficultyActivity()
        }
        button5.setOnClickListener {
            DataManager.subject = (1..4).random()
            startDifficultyActivity()
        }
    }

    open fun startDifficultyActivity() {
        val intent = Intent(this@MainActivity, DifficultyActivity::class.java)
        startActivity(intent)
    }
}
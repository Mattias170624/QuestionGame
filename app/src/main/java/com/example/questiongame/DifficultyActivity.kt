package com.example.questiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

open class DifficultyActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)

    }

    override fun onClick(buttonPressed: View) {

        when (buttonPressed.id) {
            R.id.button1 -> {
                DataManager.difficulty = 1
                showButtonStart()
            }
            R.id.button2 -> {
                DataManager.difficulty = 2
                showButtonStart()
            }
            R.id.button3 -> {
                DataManager.difficulty = 3
                showButtonStart()
            }
            R.id.buttonStart -> {
                startGameActivity()
            }
        }
    }

    open fun showButtonStart() {
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.visibility = View.VISIBLE
        buttonStart.setOnClickListener(this)
    }

    open fun startGameActivity() {
        val intent = Intent(this@DifficultyActivity, GameActivity::class.java)
        startActivity(intent)
    }
}
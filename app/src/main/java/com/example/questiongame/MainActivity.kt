package com.example.questiongame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


open class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(buttonPressed: View) {
        when (buttonPressed.id) {
            R.id.button1 -> startDifficultyActivity(subject = 1)
            R.id.button2 -> startDifficultyActivity(subject = 2)
            R.id.button3 -> startDifficultyActivity(subject = 3)
            R.id.button4 -> startDifficultyActivity(subject = 4)
            R.id.button5 -> startDifficultyActivity(subject = (1..4).random())
        }
    }

    open fun startDifficultyActivity(subject: Int) {
        val intent = Intent(this@MainActivity, DifficultyActivity::class.java)
        intent.putExtra("subject", subject)
        startActivity(intent)
    }
}
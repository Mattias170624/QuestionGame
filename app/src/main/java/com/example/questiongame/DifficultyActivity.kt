package com.example.questiongame

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

        val subject = intent.getIntExtra("subject", 0)
        Log.d("!!!activity2", "Selected subject = $subject")

    }

    override fun onClick(buttonPressed: View) {
        when (buttonPressed.id) {
            R.id.button1 -> Log.d("!!!activity2", "knapp 1")
            R.id.button2 -> Log.d("!!!activity2", "knapp 2")
            R.id.button3 -> Log.d("!!!activity2", "knapp 3")
        }
    }
}
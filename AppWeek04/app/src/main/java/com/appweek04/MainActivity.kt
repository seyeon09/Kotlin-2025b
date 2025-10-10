package com.appweek04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGreeting = findViewById<Button>(R.id.buttonGreeting)
        val buttonCounter = findViewById<Button>(R.id.buttonCount)

        buttonGreeting.setOnClickListener{
            startActivity(Intent(this, GreetingActivity::class.java))
        }

        buttonCounter.setOnClickListener {
            startActivity(Intent(this, CounterActivity::class.java))
        }
    }
}
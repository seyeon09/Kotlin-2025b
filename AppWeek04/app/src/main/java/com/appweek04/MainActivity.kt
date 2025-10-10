package com.appweek04


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonColor = findViewById<Button>(R.id.buttonColor)
        val buttonGreeting = findViewById<Button>(R.id.buttonGreeting)
        val buttonCounter = findViewById<Button>(R.id.buttonCount)

        buttonGreeting.setOnClickListener {
            startActivity(Intent(this, GreetingActivity::class.java))
        }

        buttonColor.setOnClickListener {
            startActivity(Intent(this, ColorActivity::class.java))
        }

        buttonCounter.setOnClickListener {
            startActivity(Intent(this, CounterActivity::class.java))
        }
    }
}
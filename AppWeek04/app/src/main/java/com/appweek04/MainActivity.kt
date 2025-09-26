package com.appweek04

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTxteName)
        val buttonGreet = findViewById<Button>(R.id.buttonGreet)
        val textViewGreeting = findViewById<TextView>(R.id.textViewGreeting)

        buttonGreet.setOnClickListener(){
            val name = editTextName.text.toString().trim()

            if(name.isNotEmpty()){
                textViewGreeting.text="안녕하세요 $(name)님 !"
                textViewGreeting.visibility = View.VISIBLE
            } else{
                textViewGreeting.text="나의 이름은?"
                textViewGreeting.visibility = View.VISIBLE
            }
        }
    }
}
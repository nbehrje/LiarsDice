package com.example.liarsdice

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
       // val dice = intent.getIntArrayExtra("dice")

        val result = intent.getStringExtra("result")
        var resultText: TextView = findViewById(R.id.resultText)
        resultText.text = result
    }
}
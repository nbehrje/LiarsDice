package com.example.liarsdice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        playButton?.setOnClickListener {
            var numPlayers = findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString().toInt()
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("numPlayers", numPlayers)
            startActivity(intent)
        }
    }
}
package com.example.liarsdice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        playButton?.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("numPlayers", 2)
            startActivity(intent)
        }
    }
}
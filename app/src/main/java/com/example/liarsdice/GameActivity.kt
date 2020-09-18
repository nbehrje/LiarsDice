package com.example.liarsdice

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children

class GameActivity : AppCompatActivity() {
    var numPlayers: Int = 2
    var playerStates = arrayOfNulls<PlayerState>(2)
    var activePlayer = 0
    var preRound = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var cupImage = findViewById<ImageView>(R.id.cupView)
        var diceView = findViewById<LinearLayout>(R.id.diceView)
        var passButton = findViewById<Button>(R.id.passButton)

        //Initialize game state
        val intent = intent
        numPlayers = intent.getIntExtra("numPlayers", 2)
        for(i in 0..numPlayers-1){
            playerStates[i] = PlayerState()
        }

        //Cup Image
        cupImage.setOnClickListener {
            it.visibility = View.GONE
            diceView.visibility = View.VISIBLE
        }

        //Dice Images
        for(i in 0..4){
            var v = diceView.getChildAt(i)
            var die = playerStates[activePlayer]?.dice?.get(i)
            if(v is ImageView){
                v.setImageResource(when {
                    die == 1 -> R.drawable.die1
                    die == 2 -> R.drawable.die2
                    die == 3 -> R.drawable.die3
                    die == 4 -> R.drawable.die4
                    die == 5 -> R.drawable.die5
                    else -> R.drawable.die1
                })
            }
        }

        //Pass Button
        passButton.setOnClickListener{
            activePlayer++
            if(activePlayer >= numPlayers) {
                activePlayer = 0
            }
        }
    }
}
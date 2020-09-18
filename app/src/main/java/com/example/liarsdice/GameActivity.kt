package com.example.liarsdice

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
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
    lateinit var cupImage: ImageView
    lateinit var diceView: LinearLayout
    lateinit var passButton: Button
    lateinit var buttonsView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        cupImage = findViewById<ImageView>(R.id.cupView)
        diceView = findViewById<LinearLayout>(R.id.diceView)
        passButton = findViewById<Button>(R.id.passButton)
        buttonsView = findViewById<LinearLayout>(R.id.buttonsView)

        //Initialize game state
        val intent = intent
        numPlayers = intent.getIntExtra("numPlayers", 2)
        for(i in 0..numPlayers-1){
            playerStates[i] = PlayerState()
        }

        //Initialize cup click
        cupImage.setOnClickListener {
            it.visibility = View.GONE
            diceView.visibility = View.VISIBLE
            if(preRound) {
                passButton.visibility = View.VISIBLE
            } else {
                buttonsView.visibility = View.VISIBLE
            }
        }

        //Pass Button
        passButton.setOnClickListener{
            activePlayer++
            if(activePlayer >= numPlayers) {
                activePlayer = 0
                preRound = false
            }
            setUpViews()
        }

        setUpViews()
    }

    fun setUpViews(){
        //Cup Image
        cupImage.setImageResource(when{
            activePlayer == 0 -> R.drawable.cup_red
            activePlayer == 1 -> R.drawable.cup_blue
            else -> R.drawable.cup_red
        })
        cupImage.visibility = View.VISIBLE

        //Dice
        diceView.visibility = View.INVISIBLE
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
        passButton.visibility = View.INVISIBLE
    }
}
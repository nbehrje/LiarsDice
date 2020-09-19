package com.example.liarsdice

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {
    var numPlayers: Int = 2
    var playerStates = arrayOfNulls<PlayerState>(2)
    var activePlayer = 0
    var preRound = true
    lateinit var cupImage: ImageView
    lateinit var diceView: LinearLayout
    lateinit var passButton: Button
    lateinit var buttonsView: LinearLayout
    lateinit var bidButton: Button
    lateinit var liarButton: Button
    lateinit var spinner: Spinner
    lateinit var enterBidButton: Button
    lateinit var bidText: TextView
    lateinit var popupWindow: PopupWindow
    var bid: Pair<Int,Int> = Pair(0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        cupImage = findViewById(R.id.cupView)
        diceView = findViewById(R.id.diceView)
        passButton = findViewById(R.id.passButton)
        buttonsView = findViewById(R.id.buttonsView)
        bidButton = findViewById(R.id.bidButton)
        liarButton = findViewById(R.id.liarButton)
        bidText = findViewById(R.id.bidText)

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
            passTurn()
        }

        //Popup
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.window_bid, null)
        spinner = popupView.findViewById(R.id.spinner)

        //Bid Button
        bidButton.setOnClickListener{
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0);
        }

        //Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.dice_array,
            android.R.layout.simple_spinner_item).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }

        //Bid Confirm button
        enterBidButton = popupView.findViewById(R.id.enterBidButton)
        enterBidButton.setOnClickListener{
            val quantity = popupView.findViewById<EditText>(R.id.editQty).text.toString().toInt()
            val face = spinner.selectedItemPosition+1
            if(quantity > bid.first || (face > bid.second && quantity >= bid.first)){
                bid = Pair(quantity,face)
                popupWindow.dismiss()
                passTurn()
            }
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
        diceView.visibility = View.GONE
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

        //Bid
        bidText.setText(when{
            bid.first == 0 -> "No bids"
            else -> "Last bid: "+ bid.first + " " + bid.second + "s"
        })

        //Action Buttons
        buttonsView.visibility = View.INVISIBLE
        
    }

    fun passTurn(){
        activePlayer++
        if(activePlayer >= numPlayers) {
            activePlayer = 0
            preRound = false
        }
        setUpViews()
    }
}
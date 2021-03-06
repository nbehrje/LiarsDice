package com.example.liarsdice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var closeButton: Button = findViewById(R.id.closeButton)
        closeButton.setOnClickListener{
            finish()
        }
        val result = intent.getStringExtra("result")
        var resultText: TextView = findViewById(R.id.resultText)
        resultText.text = result

        val dice1 = intent.getIntArrayExtra("dice1")
        val p1Dice: LinearLayout = findViewById(R.id.p1dice)
        populateDice(dice1, p1Dice)
        val dice2 = intent.getIntArrayExtra("dice2")
        val p2Dice: LinearLayout = findViewById(R.id.p2dice)
        populateDice(dice2, p2Dice)

        val dice3 = intent.getIntArrayExtra("dice3") ?: return
        val p3Dice: LinearLayout = findViewById(R.id.p3dice)
        p3Dice.visibility = View.VISIBLE
        populateDice(dice3, p3Dice)

        val dice4 = intent.getIntArrayExtra("dice4") ?: return
        val p4Dice: LinearLayout = findViewById(R.id.p4dice)
        p4Dice.visibility = View.VISIBLE
        populateDice(dice4, p4Dice)
    }

    fun populateDice(dice: IntArray, diceLayout: LinearLayout){
        for(i in 0..4){
            var v = diceLayout.getChildAt(i+1)
            if(v is ImageView){
                v.setImageResource(when {
                    dice[i] == 1 -> R.drawable.die1
                    dice[i] == 2 -> R.drawable.die2
                    dice[i] == 3 -> R.drawable.die3
                    dice[i] == 4 -> R.drawable.die4
                    dice[i] == 5 -> R.drawable.die5
                    dice[i] == 6 -> R.drawable.die6
                    else -> R.drawable.die1
                })
            }

        }
    }
}
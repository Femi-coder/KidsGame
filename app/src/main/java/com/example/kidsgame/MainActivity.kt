package com.example.kidsgame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var secretNumber = 0
    private var guessCount = 0

    private lateinit var guessInput: EditText
    private lateinit var messageText: TextView
    private lateinit var guessCountText: TextView
    private lateinit var submitButton: Button
    private lateinit var playAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        guessInput = findViewById(R.id.guessInput)
        messageText = findViewById(R.id.messageText)
        guessCountText = findViewById(R.id.guessCountText)
        submitButton = findViewById(R.id.submitButton)
        playAgainButton = findViewById(R.id.playAgainButton)

        startNewGame()

        submitButton.setOnClickListener {
            val guess = guessInput.text.toString().toIntOrNull()
            if (guess != null) {
                checkGuess(guess)
            } else {
                messageText.text = "Please enter a valid number!"
            }
            guessInput.text.clear()
        }

        playAgainButton.setOnClickListener {
            startNewGame()
        }
    }

    private fun startNewGame() {
        secretNumber = Random.nextInt(1, 1001)
        guessCount = 0
        messageText.text = ""
        guessCountText.text = "Guesses: 0"
        guessInput.isEnabled = true
        submitButton.isEnabled = true
        playAgainButton.visibility = Button.GONE
    }

    private fun checkGuess(guess: Int) {
        guessCount++
        when {
            guess < secretNumber -> messageText.text = "Higher!"
            guess > secretNumber -> messageText.text = "Lower!"
            else -> {
                messageText.text = "🎉 Correct! You guessed it in $guessCount guesses!"
                guessInput.isEnabled = false
                submitButton.isEnabled = false
                playAgainButton.visibility = Button.VISIBLE
            }
        }
        guessCountText.text = "Guesses: $guessCount"
    }
}

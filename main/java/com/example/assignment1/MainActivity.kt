package com.example.assignment1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextTimeOfDay: EditText
    private lateinit var buttonGetSuggest: Button
    private lateinit var textViewSuggest: TextView
    private lateinit var buttonReset: Button

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        editTextTimeOfDay = findViewById(R.id.editTextTimeOfDay)
        buttonGetSuggest = findViewById(R.id.buttonGetSuggestion)
        textViewSuggest = findViewById(R.id.textViewSuggestion)
        buttonReset = findViewById(R.id.buttonReset)

        // Set up onClick listener for Get Suggestion button
        buttonGetSuggest.setOnClickListener {
            val timeOfDayInput = editTextTimeOfDay.text.toString().trim().lowercase()

            // Get suggestion based on input time of day
            val suggestion = getSocialSparkSuggestion(timeOfDayInput)

            if (suggestion.isNotEmpty()) {
                textViewSuggest.text = suggestion
            } else {
                Toast.makeText(
                    this,
                    "Please enter a valid time of day (Morning, Afternoon, etc.)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        buttonReset.setOnClickListener {
            editTextTimeOfDay.text.clear()
            textViewSuggest.text = "Display"
        }
    }

    // Function to provide a suggestion based on the input time of day
    private fun getSocialSparkSuggestion(timeOfDay: String): String {
        return when (timeOfDay) {
            "morning" -> "Send a 'Good morning' text to a family member."
            "mid-morning" -> "Reach out to a colleague with a quick 'Thank you'."
            "afternoon" -> "Share a funny meme or interest link with a friend."
            "afternoon snack time" -> "Send a quick 'Thinking of you' message."
            "dinner" -> "Call a friend or relative for a 5-minute catch-up."
            "after dinner", "night" -> "Leave a thoughtful comment on a friend's post."
            else -> "" // Return empty if invalid input
        }
    }
}

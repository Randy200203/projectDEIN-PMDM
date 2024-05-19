package com.example.quizwiz

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    lateinit var playButton: Button
    lateinit var exitButton: Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Find the Let's Quiz button by its ID
        playButton = findViewById(R.id.play_btn)
        // Set a click listener for the Let's Quiz button
        playButton.setOnClickListener {
            // When the button is clicked, start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Find the Exit App button by its ID
        exitButton = findViewById(R.id.exit_btn)
        // Set a click listener for the Exit App button
        exitButton.setOnClickListener {
            // When the button is clicked, finish the current activity (which exits the app)
            finishAffinity()
        }
    }
}

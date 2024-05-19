package com.example.quizwiz

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The second activity of the QuizWiz app.
 *
 * This activity provides options for the user to either start the quiz or exit the app.
 *
 * @constructor Creates an instance of MainActivity2.
 */
class MainActivity2 : AppCompatActivity() {

    /**
     * Button to start the quiz.
     */
    lateinit var playButton: Button

    /**
     * Button to exit the app.
     */
    lateinit var exitButton: Button

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     */
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

package com.example.quizwiz

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizwiz.databinding.ActivityQuizBinding
import com.example.quizwiz.databinding.ScoreDialogBinding

/**
 * The activity for conducting the quiz in the QuizWiz app.
 *
 * This activity allows users to answer quiz questions within a specified time limit.
 *
 * @constructor Creates an instance of QuizActivity.
 */
class QuizActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * List of question models for the quiz.
     */
    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time: String = ""
    }

    /**
     * View binding for the activity.
     */
    lateinit var binding: ActivityQuizBinding

    /**
     * Index of the current question being displayed.
     */
    var currentQuestionIndex = 0

    /**
     * Selected answer by the user.
     */
    var selectedAns = ""

    /**
     * Score obtained by the user.
     */
    var score = 0

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the back button by its ID
        val backButton = findViewById<ImageView>(R.id.back_button)

        // Set click listener for the back button
        backButton.setOnClickListener {
            onBackPressed() // Navigate back to the previous activity
        }

        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()
        startTimer()
    }

    /**
     * Starts the countdown timer for the quiz.
     */
    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndictorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                finishQuiz()
            }
        }.start()
    }

    /**
     * Loads questions for the quiz.
     */
    private fun loadQuestions() {
        selectedAns = ""
        if (currentQuestionIndex == questionModelList.size) {
            finishQuiz()
            return
        }

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1}/ ${questionModelList.size}"
            quizProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }

    /**
     * Handles click events for buttons.
     *
     * @param view The view that was clicked.
     */
    override fun onClick(view: View?) {

        binding.apply {
            btn0.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.grey))
            btn1.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.grey))
            btn2.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.grey))
            btn3.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.grey))
        }
        val clickedBtn = view as Button
        if (clickedBtn.id == R.id.next_btn) {
            //next button
            if (selectedAns == questionModelList[currentQuestionIndex].correct) {
                score++
            }
            currentQuestionIndex++
            loadQuestions()
        } else {
            //options is clicked
            selectedAns = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        }
    }

    /**
     * Finishes the quiz and displays the score.
     */
    private fun finishQuiz() {
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if (percentage > 50) {
                scoreTitle.text = "Congrats on passing the quiz! Well done!"
                scoreTitle.setTextColor(Color.BLUE)
            } else {
                scoreTitle.text = "Sorry you didn't pass. Keep trying!"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text = "$score out of $totalQuestions are correct."
            finishBtn.setOnClickListener {
                finish()
            }
        }
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }
}


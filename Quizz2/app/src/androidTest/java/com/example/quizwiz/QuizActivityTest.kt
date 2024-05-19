package com.example.quizwiz

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class QuizActivityTest{
    @org.junit.jupiter.api.Test
    fun test_MainActivity_carga() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()))
    }

}
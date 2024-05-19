package com.example.quizwiz

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivity2InstrumentedTest {

    @Test
    fun testPlayButtonClick() {
        // Inicia la actividad MainActivity2
        val scenario = ActivityScenario.launch(MainActivity2::class.java)

        // Hace clic en el botón "Let's Quiz"
        onView(withId(R.id.play_btn)).perform(click())

        // Verifica que la actividad MainActivity se haya iniciado
        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testExitButtonClick() {
        // Inicia la actividad MainActivity2
        val scenario = ActivityScenario.launch(MainActivity2::class.java)

        // Hace clic en el botón "Exit App"
        onView(withId(R.id.exit_btn)).perform(click())

        // Verifica que la actividad se haya cerrado
        scenario.onActivity { activity ->
            assertTrue(activity.isFinishing)
        }
    }
}

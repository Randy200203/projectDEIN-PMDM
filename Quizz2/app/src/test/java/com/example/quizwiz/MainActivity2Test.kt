package com.example.quizwiz

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import java.util.regex.Pattern.matches


class MainActivity2Test {

    @Test
    fun test_MainActivity2() {
        val activityScenario = ActivityScenario.launch(MainActivity2::class.java)
        onView(withId(R.id.activity_main2)).check(matches(isDisplayed()))
    }

    @Test
    fun testPlayButtonClick() {
        // Inicia la actividad principal
        val scenario = ActivityScenario.launch(MainActivity2::class.java)

        // Hace clic en el botón de iniciar sesión
        onView(withId(R.id.play_btn)).perform(click())

        // Verifica que la actividad de inicio de sesión se haya iniciado
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



//    @Test
//    fun test_MainActivity2_carga() {
//        // Inicia la actividad MainActivity2
//        val activityScenario = ActivityScenario.launch(MainActivity2::class.java)
//
//        // Verifica que el layout principal de MainActivity2 se muestra en pantalla
//        onView(withId(R.id.activity_main2)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testPlayButtonClick() {
//        // Inicia la actividad MainActivity2
//        val scenario = ActivityScenario.launch(MainActivity2::class.java)
//
//        // Hace clic en el botón "Let's Quiz"
//        onView(withId(R.id.play_btn)).perform(click())
//
//        // Verifica que la actividad MainActivity se haya iniciado
//        onView(withId(R.id.main_activity_layout)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testExitButtonClick() {
//        // Inicia la actividad MainActivity2
//        val scenario = ActivityScenario.launch(MainActivity2::class.java)
//
//        // Hace clic en el botón "Exit App"
//        onView(withId(R.id.exit_btn)).perform(click())
//
//        // Verifica que la actividad se haya cerrado
//        scenario.onActivity { activity ->
//            assertTrue(activity.isFinishing)
//        }
//    }
}

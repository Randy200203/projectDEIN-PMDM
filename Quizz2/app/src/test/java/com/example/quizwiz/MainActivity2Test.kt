package com.example.quizwiz


import android.app.Activity
import android.content.Intent
import android.widget.Button
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MainActivity2Test {

    @Mock
    private lateinit var mockIntent: Intent

    @Test
    fun testPlayButtonClick() {
        val mainActivity2 = MainActivity2()
        val mockButton = mock(Button::class.java)
        mainActivity2.playButton = mockButton

        // Simular clic en el botón
        mainActivity2.playButton.performClick()

        // Verificar que se inicie la actividad MainActivity
        verify(mockButton.context).startActivity(mockIntent)
    }

    @Test
    fun testExitButtonClick() {
        val mainActivity2 = MainActivity2()
        val mockActivity = mock(Activity::class.java)
        mainActivity2.exitButton = mock(Button::class.java)

        // Simular clic en el botón
        mainActivity2.exitButton.performClick()

        // Verificar que se llama al método finish() en la actividad
        verify(mockActivity).finish()
    }
}
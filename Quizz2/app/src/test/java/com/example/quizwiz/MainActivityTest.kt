package com.example.quizwiz

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizwiz.databinding.ActivityMainBinding
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MainActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var binding: ActivityMainBinding

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        binding = activity.binding
    }

    @Test
    fun testSetupRecycleView() {
        // Arrange
        activity.quizModellist = mutableListOf(
            QuizModel("1", "Quiz 1", "Description 1", "5", emptyList()),
            QuizModel("2", "Quiz 2", "Description 2", "10", emptyList())
        )

        // Act
        activity.setupRecycleView()

        // Assert
        assertEquals(View.GONE, binding.progressBar.visibility)
        assertNotNull(binding.recyclerViewItems.adapter)
        assertTrue(binding.recyclerViewItems.layoutManager is LinearLayoutManager)
        assertEquals(2, binding.recyclerViewItems.adapter?.itemCount)
    }
}
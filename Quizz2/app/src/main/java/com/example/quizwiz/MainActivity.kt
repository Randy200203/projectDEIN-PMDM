package com.example.quizwiz

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizwiz.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModellist: MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the toolbar and set it as the support action bar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        quizModellist = mutableListOf()
        getDataFromFirebase()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this@MainActivity, "Closing app...", Toast.LENGTH_SHORT).show()
                finish() // This will close the activity
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupRecycleView() {
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModellist)
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewItems.adapter = adapter
    }

    private fun getDataFromFirebase() {
        binding.progressBar.visibility = View.VISIBLE
        // Dummy data commented out, assuming real data will be fetched from Firebase

        // Return the references of the database in Firebase
        FirebaseDatabase.getInstance().reference
            // Returns all the list of Quiz models we have
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        // Get each single quiz model
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            // Adding every quiz model to our quiz model list
                            quizModellist.add(quizModel)
                        }
                    }
                }
                setupRecycleView()
            }
    }
}

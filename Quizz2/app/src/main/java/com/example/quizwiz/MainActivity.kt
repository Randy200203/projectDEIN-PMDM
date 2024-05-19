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

/**
 * The main activity of the QuizWiz app.
 *
 * This activity displays a list of quiz items retrieved from Firebase Database.
 * It also provides options in the toolbar menu for users to interact with the app.
 *
 * @constructor Creates an instance of MainActivity.
 */
class MainActivity : AppCompatActivity() {

    /**
     * View binding for the activity layout.
     */
    lateinit var binding: ActivityMainBinding

    /**
     * List of quiz models retrieved from Firebase.
     */
    lateinit var quizModellist: MutableList<QuizModel>

    /**
     * Adapter for the RecyclerView displaying quiz items.
     */
    internal lateinit var adapter: QuizListAdapter

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     */
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

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return false it will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_item, menu)
        return true
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     *
     * @param item The menu item that was selected.
     * @return [Boolean] Return false to allow normal menu processing to proceed, true to consume it here.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this@MainActivity, "Closing app...", Toast.LENGTH_SHORT).show()
                finishAffinity() // This will close the activity
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Set up the RecyclerView to display quiz items.
     */
    fun setupRecycleView() {
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModellist)
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewItems.adapter = adapter
    }

    /**
     * Retrieve quiz data from Firebase Database.
     */
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


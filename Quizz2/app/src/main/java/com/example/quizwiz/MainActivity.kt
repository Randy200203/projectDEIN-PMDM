package com.example.quizwiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        quizModellist = mutableListOf()
        getDataFromFirebase()
    }

    private fun setupRecycleView(){
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModellist)
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewItems.adapter = adapter


    }

    private fun getDataFromFirebase(){
        binding.progressBar.visibility = View.VISIBLE
        //dummydata

//        val listQuestionModel = mutableListOf<QuestionModel>()
//        listQuestionModel.add(QuestionModel("What is android?", mutableListOf("language", "OS","Product", "None"), "OS"))
//        listQuestionModel.add(QuestionModel("What is IOS?", mutableListOf("language", "Iphone","Product", "None"), "Iphone"))
//        listQuestionModel.add(QuestionModel("Best game?", mutableListOf("language", "Iphone","COD", "None"), "COD"))
//
//
//        quizModellist.add(QuizModel("1","Programming", "All the basic programming", "10",listQuestionModel))
////        quizModellist.add(QuizModel("2","Computer", "All the basic computers", "20"))
////        quizModellist.add(QuizModel("3","Gaming", "All the games", "15"))
//

          //Return the references of the database in Firebase
        FirebaseDatabase.getInstance().reference
            //returns all the list of Quiz models we have
            .get()
            .addOnSuccessListener { dataSnapShot ->
                if (dataSnapShot.exists()){
                    for (snapshot in dataSnapShot.children){
                        //get each single quizmodel
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            //adding every quizmodel to our quizmodellist
                            quizModellist.add(quizModel)
                        }
                    }

                }
                setupRecycleView()
            }

    }
}
package com.example.quizwiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.databinding.QuizItemRecycleRowBinding

class QuizListAdapter(private val quizModelList : List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: QuizItemRecycleRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model :QuizModel){
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = model.time + " min"
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecycleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
       return  quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}
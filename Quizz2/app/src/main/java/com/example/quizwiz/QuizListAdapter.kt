package com.example.quizwiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.databinding.QuizItemRecycleRowBinding

/**
 * Adapter class for the RecyclerView that displays quiz items in the QuizWiz app.
 *
 * This adapter binds quiz data to the corresponding views in the RecyclerView.
 *
 * @property quizModelList The list of quiz models to be displayed.
 * @constructor Creates an instance of QuizListAdapter.
 */
class QuizListAdapter(private val quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    /**
     * ViewHolder class for the RecyclerView items.
     *
     * @param binding The view binding for the item layout.
     */
    class MyViewHolder(private val binding: QuizItemRecycleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the quiz model data to the views.
         *
         * @param model The quiz model to be displayed.
         */
        fun bind(model: QuizModel) {
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = model.time + " min"
                root.setOnClickListener {
                    val intent = Intent(root.context, QuizActivity::class.java)
                    QuizActivity.questionModelList = model.questionList
                    QuizActivity.time = model.time
                    root.context.startActivity(intent)
                }
            }
        }
    }

    /**
     * Called when RecyclerView needs a new [MyViewHolder] of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new [MyViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            QuizItemRecycleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return quizModelList.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder that should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}

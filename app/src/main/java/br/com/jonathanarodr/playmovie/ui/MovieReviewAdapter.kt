package br.com.jonathanarodr.playmovie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jonathanarodr.playmovie.R.id
import br.com.jonathanarodr.playmovie.R.layout
import br.com.jonathanarodr.playmovie.model.MovieReview

class MovieReviewAdapter : Adapter<MovieReviewAdapter.MovieReviewViewHolder>() {

    var reviews: List<MovieReview> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MovieReviewViewHolder(itemView: View) : ViewHolder(itemView) {
        val author: TextView = itemView.findViewById(id.author_review)
        val content: TextView = itemView.findViewById(id.content_review)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(layout.item_review, parent, false)

        return MovieReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val (_, author, content) = reviews[position]
        holder.author.text = author.trim()
        holder.content.text = content.trim()
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}
package br.com.jonathanarodr.playmovie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jonathanarodr.playmovie.R
import br.com.jonathanarodr.playmovie.R.id
import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.util.GlideApp

class MovieAdapter(private val clickHandler: MovieAdapterOnClickHandler) :
    Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(itemView: View) : ViewHolder(itemView), OnClickListener {
        val poster: ImageView = itemView.findViewById(id.poster)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            clickHandler.onClick(this@MovieAdapter.movies[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater
            .from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        this.movies[position].let {
            GlideApp.with(holder.itemView.context)
                .load(it.getPosterDefault())
                .centerCrop()
                .placeholder(R.drawable.backgroud_grey)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int = movies.size
}
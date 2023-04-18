package br.com.jonathanarodr.playmovie.feature.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.databinding.ListItemMovieBinding
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel

class MovieAdapter(
    private val onClickHandler: MovieOnClickHandler,
) : Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<MovieUiModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun interface MovieOnClickHandler {
        fun onMovieClickListener(movie: MovieUiModel)
    }

    inner class MovieViewHolder(itemView: View) : ViewHolder(itemView), OnClickListener {

        val binding = ListItemMovieBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onClickHandler.onMovieClickListener(
                movies[bindingAdapterPosition]
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)
            .run {
                MovieViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        this.movies[position].apply {
            if (poster.isNotEmpty()) {
                ImageLoaderUtils.load(holder.binding.poster, poster)
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}

package com.example.moviesapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.di.MOVIE_IMAGE_BASE_PATH
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter() :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    fun update(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.tvMovieTitle.text = movie.title
            itemView.tvVoteAverage.text = "${movie.vote_average}"
            itemView.tvVoteCounts.text = "${movie.vote_count}"
            Glide.with(itemView)
                .load(MOVIE_IMAGE_BASE_PATH + movie.poster_path)
                .into(itemView.imgMoviePoster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movie = movies[position])
    }
}
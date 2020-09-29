package com.example.moviesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.di.MOVIE_IMAGE_BASE_PATH
import kotlinx.android.synthetic.main.content_layout.*
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movie: Movie? = arguments?.getParcelable("movieArg") as? Movie?
        movie?.let {
            bindUI(movie)
        }
    }

    private fun bindUI(movie: Movie) {
        toolbar.title = movie.title
        tvMovieDesc.text = movie.overview
        tvMovieReleaseDate.text = movie.release_date
        tvMovieRate.text = "${movie.vote_average}/10"
        tvMovieVotingCounts.text = "${movie.vote_count}"

        Glide.with(this)
            .load(MOVIE_IMAGE_BASE_PATH + movie.poster_path)
            .into(imgMovieDetailsPoster)

        Glide.with(this)
            .load(MOVIE_IMAGE_BASE_PATH + movie.backdrop_path)
            .into(imgMovieDetailsCover)
    }
}
package com.example.moviesapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviesapp.R
import com.example.moviesapp.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MoviesFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MoviesFragmentViewModel>

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNowPlayingMovies()
    }

    private fun observeNowPlayingMovies() {
        viewModel.getMovieLiveData().observe(requireActivity(), Observer {
            if (it.isNotEmpty()) {

            }
        })
    }
}
package com.example.moviesapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.utils.VerticalSpaceItemDecoration
import com.example.moviesapp.utils.ViewModelFactory
import com.example.moviesapp.utils.afterTextChanged
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : Fragment(), MovieItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MoviesFragmentViewModel>

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel::class.java)
    }

    private val moviesAdapter = MoviesAdapter(this)

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
        initMoviesRecyclerView()
        observeNowPlayingMovies()
        initSearchEditView()

    }

    private fun initSearchEditView() {
        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Your piece of code on keyboard search click
                viewModel.search(v.text.toString())
                true
            } else false
        }


        edtSearch.afterTextChanged {
            if (it.isEmpty())
                viewModel.getNowPlayingMovies()
        }

    }

    private fun initMoviesRecyclerView() {
        rclMovies.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rclMovies.adapter = moviesAdapter
        rclMovies.addItemDecoration(VerticalSpaceItemDecoration(16))
        rclMovies.setHasFixedSize(true)

    }

    private fun observeNowPlayingMovies() {
        viewModel.getMovieLiveData().observe(requireActivity(), Observer {
            if (it.isNotEmpty()) {
                moviesAdapter.update(it)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onMovieClicked(movie: Movie) {
        val bundle = bundleOf("movieArg" to movie)
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
    }
}
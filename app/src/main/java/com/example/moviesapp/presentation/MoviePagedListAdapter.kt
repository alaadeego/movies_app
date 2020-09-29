package com.example.moviesapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import com.example.moviesapp.data.model.Status
import com.example.moviesapp.di.MOVIE_IMAGE_BASE_PATH
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_state.view.*

class MoviePagedListAdapter :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.item_movie, parent, false)
            MovieViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.item_state, parent, false)
            NetworkStateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieViewHolder).bind(movie = getItem(position))
        } else {
            (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRows() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }

    private fun hasExtraRows(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRows()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousNetworkState: NetworkState = newNetworkState
        val previousExtraRows = hasExtraRows()
        this.networkState = newNetworkState
        val newExtraRows = hasExtraRows()

        if (previousExtraRows != newExtraRows) {
            if (previousExtraRows) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (newExtraRows && previousNetworkState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie?, movieItemClickListener: MovieItemClickListener? = null) {
            itemView.tvMovieTitle.text = movie?.title
            itemView.tvVoteAverage.text = "${movie?.vote_average}"
            itemView.tvVoteCounts.text = "${movie?.vote_count}"
            Glide.with(itemView)
                .load(MOVIE_IMAGE_BASE_PATH + movie?.poster_path)
                .into(itemView.imgMoviePoster)
            itemView.setOnClickListener {
                // movieItemClickListener.onMovieClicked(movie?)
            }
        }

    }

    class NetworkStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(networkState: NetworkState?) {
            networkState?.let { state ->
                if (state.state == Status.RUNNING) {
                    itemView.progressBar.visibility = View.VISIBLE;
                } else {
                    itemView.progressBar.visibility = View.GONE;
                }

                if (state.state == Status.FAILED) {
                    itemView.tvNetworkState.visibility = View.VISIBLE;
                    itemView.tvNetworkState.text = state.message;
                } else {
                    itemView.tvNetworkState.visibility = View.GONE;
                }
            }

        }
    }

    class MovieDiffCallback() : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }


}
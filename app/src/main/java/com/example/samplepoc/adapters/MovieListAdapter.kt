package com.example.samplepoc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplepoc.R
import com.example.samplepoc.databinding.RowMovieItemBinding
import com.example.samplepoc.model.Search
import com.example.samplepoc.view.MovieListFrag

class MovieListAdapter(
    private val movies: List<Search>,
    private val listener: MovieListFrag
) :
    RecyclerView.Adapter<MovieListAdapter.MoviesHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(
            DataBindingUtil.inflate<RowMovieItemBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.row_movie_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

        holder.recyclerviewMovieBinding.movie = movies[position]
    }

    inner class MoviesHolder(val recyclerviewMovieBinding: RowMovieItemBinding) :
        RecyclerView.ViewHolder(recyclerviewMovieBinding.root) {

    }
}
package com.example.samplepoc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplepoc.R
import com.example.samplepoc.databinding.RowMovieItemBinding
import com.example.samplepoc.interfaces.RecyclerViewClickListener
import com.example.samplepoc.model.Search

class MovieListAdapter(
    private val movies: List<Search>,
    private val listener: RecyclerViewClickListener
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
        Glide.with(holder.recyclerviewMovieBinding.ivMovieIcon.context)
            .load(movies[position].poster)
            .into(holder.recyclerviewMovieBinding.ivMovieIcon)

        holder.recyclerviewMovieBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(it, movies[position])
        }
    }

    inner class MoviesHolder(val recyclerviewMovieBinding: RowMovieItemBinding) :
        RecyclerView.ViewHolder(recyclerviewMovieBinding.root) {

    }
}
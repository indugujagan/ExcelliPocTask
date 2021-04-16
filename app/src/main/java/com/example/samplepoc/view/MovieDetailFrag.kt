package com.example.samplepoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.samplepoc.databinding.FragDetailedMovieItemBinding
import com.example.samplepoc.util.Logger
import com.example.samplepoc.viemodel.MovieDetailFragVM

class MovieDetailFrag : BaseFrag() {

    private var _binding: FragDetailedMovieItemBinding? = null
    private val binding get() = _binding!!
    lateinit var vm: MovieDetailFragVM
    private var imdbID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragDetailedMovieItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    override fun setupUI() {
        setUpBase(context, vm)

        (activity as MainActivity).supportActionBar!!.hide()

        if (arguments != null) {
            imdbID = requireArguments().getString("IMDB_ID")
            Logger.logite("Imdb Id", imdbID)
        }

        vm.getMovieDetailsApiCall(imdbID)

    }

    override fun setupViewModel() {
        vm = ViewModelProvider(this).get(MovieDetailFragVM::class.java)
    }

    override fun setupObserver() {
        vm.movieDetailsResponse.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it.poster)
                .into(binding.ivPoster)
            binding.tvImdbRating.text = it.ratings.get(0).value
            binding.tvMovieName.text = it.title
            binding.tvMovieDesc.text = it.plot

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
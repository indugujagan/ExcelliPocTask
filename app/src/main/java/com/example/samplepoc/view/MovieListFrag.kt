package com.example.samplepoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.samplepoc.databinding.FragMovieListBinding
import com.example.samplepoc.viemodel.MovieListFragVM

class MovieListFrag : BaseFrag() {

    private var _binding: FragMovieListBinding? = null
    private val binding get() = _binding!!
    lateinit var vm: MovieListFragVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragMovieListBinding.inflate(inflater, container, false)
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
    }

    override fun setupViewModel() {
        vm = ViewModelProvider(this).get(MovieListFragVM::class.java)
    }

    override fun setupObserver() {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
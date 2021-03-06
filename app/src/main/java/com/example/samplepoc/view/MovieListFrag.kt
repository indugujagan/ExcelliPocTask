package com.example.samplepoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplepoc.R
import com.example.samplepoc.adapters.MovieListAdapter
import com.example.samplepoc.databinding.FragMovieListBinding
import com.example.samplepoc.interfaces.RecyclerViewClickListener
import com.example.samplepoc.model.Search
import com.example.samplepoc.viemodel.MovieListFragVM

class MovieListFrag : BaseFrag(), RecyclerViewClickListener {

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

        (activity as MainActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        vm.getMoviesApiCall("Friends", 1)
    }

    override fun setupViewModel() {
        vm = ViewModelProvider(this).get(MovieListFragVM::class.java)
    }

    override fun setupObserver() {
        vm.movieListResponse.observe(viewLifecycleOwner, Observer { movieListResponse ->

            binding.rvMoviesList.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )
                it.setHasFixedSize(true)
                it.adapter =
                    MovieListAdapter(
                        movieListResponse.search,
                        this
                    )

            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar!!.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(view: View, movieItem: Search) {
        val args = Bundle()
        args.putString("IMDB_ID", movieItem.imdbID)
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailFragment, args)
    }
}
package com.dilauro.testmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dilauro.testmovie.adapter.MoviesListAdapter
import com.dilauro.testmovie.data.dataclasses.Movies
import com.dilauro.testmovie.databinding.ActivityMoviesListBinding
import com.dilauro.testmovie.model.MovieListViewModel

class MoviesList : AppCompatActivity() {
    private lateinit var mBinding: ActivityMoviesListBinding
    private lateinit var moviesListAdapter: MoviesListAdapter
    private lateinit var movieListViewModel: MovieListViewModel
    var offset = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViewModel()
        mBinding.moviesListRW.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                val allItems = recyclerView.layoutManager?.itemCount
                if (allItems != null) {
                    if (lastPosition == (allItems - 1)) {
                        offset += 20
                        updateViewModel()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        mBinding.moviesListRW.adapter = moviesListAdapter
    }

    private fun initViewModel() {
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieListViewModel.getLiveDataObserver().observe(this, object : Observer<Movies> {
            override fun onChanged(t: Movies?) {
                if (t != null) {
                    if (offset == 0) {
                        moviesListAdapter = MoviesListAdapter(t)
                        initRecyclerView()
                        mBinding.moviesListRW.layoutManager = LinearLayoutManager(this@MoviesList)
                    } else {
                        moviesListAdapter.notifyDataSetChanged()
                        moviesListAdapter.updateItems(t.results)
                    }
                } else {
                    Toast.makeText(this@MoviesList, "Не удалось загрузить данные", Toast.LENGTH_SHORT).show()
                }
            }
        })
        movieListViewModel.getApiCall(offset)
    }

    private fun updateViewModel() {
        movieListViewModel.getApiCall(offset)
    }
}
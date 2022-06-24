package com.dilauro.testmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dilauro.testmovie.R
import com.dilauro.testmovie.data.dataclasses.Movies
import com.dilauro.testmovie.data.dataclasses.Result
import com.dilauro.testmovie.databinding.MoviesItemBinding
import com.squareup.picasso.Picasso

class MoviesListAdapter(private val moviesList: Movies) : RecyclerView.Adapter<MoviesListAdapter.MoviesListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MoviesListHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.results.size
    }

    override fun onBindViewHolder(holder: MoviesListHolder, position: Int) {
        with(moviesList.results[position]) {
            if (display_title != null) {
                holder.title.text = display_title
            } else {
                holder.title.visibility = View.INVISIBLE
            }

            if (summary_short != null) {
                holder.description.text = summary_short
            } else {
                holder.description.visibility = View.INVISIBLE
            }

            if (multimedia.src != null) {
                Picasso.get().load(multimedia.src).into(holder.poster)
            } else {
                holder.poster.setImageResource(R.drawable.no_image)
            }
        }
    }

    class MoviesListHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var mBinding = MoviesItemBinding.bind(item)
        val title = mBinding.movieTitle
        val poster = mBinding.moviePoster
        val description = mBinding.movieDescription
    }

    fun updateItems(newItems: MutableList<Result>) {
        this.moviesList.results.addAll(newItems)
    }

}
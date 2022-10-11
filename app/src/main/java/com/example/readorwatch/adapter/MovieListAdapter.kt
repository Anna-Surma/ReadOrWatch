package com.example.readorwatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.readorwatch.R
import com.example.readorwatch.model.MovieItem
import com.example.readorwatch.model.OpenDetailsEvent
import com.example.readorwatch.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val movieResponseItemList: List<MovieItem>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.itemMovieId = movieResponseItemList[position].id
        holder.itemTitle.text = movieResponseItemList[position].title
        Picasso.get().load(movieResponseItemList[position].posterXlUrl)
            .error(R.drawable.app_logo)
            .into(holder.itemImage)


        holder.itemImage.setOnClickListener {
            if (holder.itemMovieId != null) {
                viewModel.setDetailsEvent(OpenDetailsEvent(holder.itemMovieId))
            }
        }
    }

    override fun getItemCount(): Int {
        return movieResponseItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemMovieId: Int? = null
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
    }
}
package com.kkonradsoftware.feature.presentation.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kkonradsoftware.feature.presentation.databinding.ItemNewMovieBinding
import com.kkonradsoftware.feature.presentation.list.adapter.viewholder.NewMovieViewHolder
import com.kkonradsoftware.feature.presentation.list.model.MovieListItemData
import com.kkonradsoftware.libraries.ds.listitem.ListItemData
import javax.inject.Inject

class NewMovieListAdapter @Inject constructor() :
    PagingDataAdapter<MovieListItemData, NewMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieViewHolder =
        NewMovieViewHolder(
            binding = ItemNewMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    override fun onBindViewHolder(holder: NewMovieViewHolder, position: Int) {
        Log.e("TEST_KK", "$position -> ${getItem(position)}")
        getItem(position)?.let(holder::onBind)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieListItemData>() {
            override fun areItemsTheSame(oldItem: MovieListItemData, newItem: MovieListItemData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieListItemData, newItem: MovieListItemData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
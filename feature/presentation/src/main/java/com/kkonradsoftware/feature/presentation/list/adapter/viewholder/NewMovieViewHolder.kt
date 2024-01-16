package com.kkonradsoftware.feature.presentation.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.kkonradsoftware.feature.presentation.databinding.ItemNewMovieBinding
import com.kkonradsoftware.feature.presentation.list.model.MovieListItemData
import com.kkonradsoftware.libraries.ds.BR

class NewMovieViewHolder(
    private val binding: ItemNewMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(movieListItemData: MovieListItemData) {
        binding.setVariable(BR.itemData, movieListItemData)
    }
}
package com.kkonradsoftware.libraries.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun loadImage(imageView: ImageView, path: String) {
        Glide.with(imageView)
            .load("${BuildConfig.IMAGE_ROOT}$path")
            .into(imageView)
    }
}
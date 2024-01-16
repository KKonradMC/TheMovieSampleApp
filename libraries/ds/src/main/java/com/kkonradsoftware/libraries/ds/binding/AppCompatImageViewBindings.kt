package com.kkonradsoftware.libraries.ds.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.kkonradsoftware.libraries.imageloader.ImageLoader

@BindingAdapter("imageRes")
fun AppCompatImageView.imageRes(imageRes: Int?) {
    imageRes?.let(::setImageResource)
}
@BindingAdapter("imagePath")
fun AppCompatImageView.imagePath(imagePath: String?) {
    imagePath?.let{
        ImageLoader.loadImage(imageView = this, path = it)
    }
}
package com.example.nimantran

import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("image")
fun loadImage(view: android.widget.ImageView, url: String) {
    view.load(url)
}
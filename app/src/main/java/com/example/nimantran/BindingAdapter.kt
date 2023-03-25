package com.example.nimantran

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import java.util.*


@BindingAdapter("image")
fun loadImage(view: android.widget.ImageView, url: String) {
    view.load(url)
}

@BindingAdapter("humanize_date_text")
fun humanizeDate(view:TextView, date: Date){
    val now = System.currentTimeMillis()
    when (val out = DateUtils.getRelativeTimeSpanString(date.time, now, DateUtils.DAY_IN_MILLIS)){
        "0" -> view.text = "Today"
        else-> view.text = out
    }
}
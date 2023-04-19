package com.omerakkoyun.mybaseapp1.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImageFromID")
fun loadImageFromID(imageView: ImageView, coinID: String) {
    imageView.loadImageFromID(coinID)
}

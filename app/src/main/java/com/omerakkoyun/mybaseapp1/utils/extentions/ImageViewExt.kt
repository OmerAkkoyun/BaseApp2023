package com.omerakkoyun.mybaseapp1.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.omerakkoyun.mybaseapp1.BuildConfig


private fun createPlaceHolder(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}

fun ImageView.loadImageFromID(coinId:String?){
    val placeholder = createPlaceHolder(this.context)
    this.load(BuildConfig.BASE_IMAGE_URL +coinId+".png"){
        crossfade(true)
        crossfade(500)
        placeholder(placeholder)
    }

}

fun ImageView.loadImageFromURL(url:String?){
    val placeholder = createPlaceHolder(this.context)
    this.load(url){
        crossfade(true)
        crossfade(500)
        placeholder(placeholder)
    }

}


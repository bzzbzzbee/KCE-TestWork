package com.bzzbzzbee.kcetestwork.utils

import android.net.Uri
import android.widget.ImageView
import com.bzzbzzbee.kcetestwork.R
import com.squareup.picasso.Picasso

object PicassoUtil {
    fun ImageView.loadImage(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_car)
            .error(R.drawable.ic_car)
            .into(this)
    }

    fun ImageView.loadImage(uri: Uri) {
        Picasso.get()
            .load(uri)
            .placeholder(R.drawable.ic_car)
            .error(R.drawable.ic_car)
            .into(this)
    }
}
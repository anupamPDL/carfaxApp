package com.anupam.carfaxapp.utils

import android.content.Context
import android.widget.ImageView
import com.anupam.carfaxapp.R
import com.bumptech.glide.Glide

object Utility {

    fun loadImageIntoView(context: Context, imageView: ImageView, stringUrl: String?) {
        Glide.with(context)
                .load(stringUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.no_imge_placeholder)
                .fallback(R.drawable.placeholder_no_image)
                .into(imageView)
    }
}


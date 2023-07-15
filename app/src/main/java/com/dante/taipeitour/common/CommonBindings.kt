package com.dante.taipeitour.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.dante.taipeitour.R

@BindingAdapter("imageUrl")
fun ImageView.bindsImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.img_placeholder)
        error(R.drawable.img_placeholder)
    }
}

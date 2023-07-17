package com.dante.taipeitour.common

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import coil.load
import com.dante.taipeitour.R

@BindingAdapter("imageUrl")
fun ImageView.bindsImage(imageUrl: String?) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.img_placeholder)
        error(R.drawable.img_placeholder)
    }
}

@BindingAdapter("circleStatus")
fun ImageView.bindsDetailsCircleStatus(isSelected: Boolean = false) {
    background = ShapeDrawable(object : Shape() {
        override fun draw(canvas: Canvas, paint: Paint) {
            paint.apply {
                color = resources.getColor(R.color.white, null)
                isAntiAlias = true
            }

            when (isSelected) {
                true -> {
                    paint.style = Paint.Style.FILL
                }

                false -> {
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth =
                        resources.getDimensionPixelSize(R.dimen.edge_detail_circle).toFloat()
                }
            }
            canvas.drawCircle(
                width / 2,
                height / 2,
                resources.getDimensionPixelSize(R.dimen.space_4).toFloat(),
                paint
            )
        }
    })
}

@BindingAdapter("addDecoration")
fun RecyclerView.bindsAddDecoration(decoration: ItemDecoration?) {
    decoration?.let {
        addItemDecoration(it)
    }
}

@BindingAdapter("url")
fun TextView.setUnderline(url: String?) {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    text = url
}

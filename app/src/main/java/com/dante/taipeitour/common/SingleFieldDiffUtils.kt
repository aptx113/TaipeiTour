package com.dante.taipeitour.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SingleFieldDiffUtils<T : Any>(val fieldExtractor: (T) -> Any?) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        fieldExtractor(oldItem) == fieldExtractor(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}

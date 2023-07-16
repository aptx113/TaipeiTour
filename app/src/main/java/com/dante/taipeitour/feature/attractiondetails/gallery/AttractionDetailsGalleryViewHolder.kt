package com.dante.taipeitour.feature.attractiondetails.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dante.taipeitour.databinding.ItemDetailsGalleryBinding

class AttractionDetailsGalleryViewHolder(private val binding: ItemDetailsGalleryBinding) :
    ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.imageUrl = imageUrl

        binding.executePendingBindings()
    }

    companion object {
        fun create(viewGroup: ViewGroup): AttractionDetailsGalleryViewHolder {
            val layoutInflater = LayoutInflater.from(viewGroup.context)
            val binding = ItemDetailsGalleryBinding.inflate(layoutInflater, viewGroup, false)
            return AttractionDetailsGalleryViewHolder(binding)
        }
    }
}

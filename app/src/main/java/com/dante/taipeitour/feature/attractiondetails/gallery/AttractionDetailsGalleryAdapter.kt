package com.dante.taipeitour.feature.attractiondetails.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dante.taipeitour.common.SingleFieldDiffUtils

class AttractionDetailsGalleryAdapter :
    ListAdapter<String, AttractionDetailsGalleryViewHolder>(SingleFieldDiffUtils { it }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttractionDetailsGalleryViewHolder {
        return AttractionDetailsGalleryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AttractionDetailsGalleryViewHolder, position: Int) {
        val realPosition = position.rem(getRealCount())
        val item = getItem(realPosition)
        holder.bind(item)
    }

    private fun getRealCount(): Int = super.getItemCount()

    override fun getItemCount(): Int {
        if (getRealCount() == 0) {
            return 0
        }
        return Int.MAX_VALUE
    }
}

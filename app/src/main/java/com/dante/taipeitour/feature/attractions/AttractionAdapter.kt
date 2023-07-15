package com.dante.taipeitour.feature.attractions

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dante.taipeitour.model.Attraction

class AttractionAdapter :
    PagingDataAdapter<Attraction, AttractionViewHolder>(AttractionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        return AttractionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object AttractionComparator : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem && oldItem.elong == newItem.elong && oldItem.nlat == newItem.nlat
        }
    }
}

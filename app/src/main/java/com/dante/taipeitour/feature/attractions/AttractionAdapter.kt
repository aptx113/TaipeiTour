package com.dante.taipeitour.feature.attractions

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dante.taipeitour.model.Attraction

class AttractionAdapter(private val onDetailsNavigated: (Attraction) -> Unit) :
    PagingDataAdapter<Attraction, AttractionViewHolder>(AttractionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        return AttractionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            item?.let { onDetailsNavigated(it) }
        }
        holder.bind(item)
    }

    object AttractionComparator : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.id == newItem.id && oldItem.images == newItem.images
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.elong == newItem.elong && oldItem.nlat == newItem.nlat && oldItem.images == newItem.images
        }
    }
}

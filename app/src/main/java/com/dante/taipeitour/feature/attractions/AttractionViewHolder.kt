package com.dante.taipeitour.feature.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dante.taipeitour.databinding.ItemAttractionBinding
import com.dante.taipeitour.model.Attraction

class AttractionViewHolder(private val viewDataBinding: ItemAttractionBinding) :
    ViewHolder(viewDataBinding.root) {
    fun bind(attraction: Attraction?) {
        if (attraction == null) return
        viewDataBinding.attraction = attraction
        viewDataBinding.executePendingBindings()
    }

    companion object {
        fun create(viewGroup: ViewGroup): AttractionViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ItemAttractionBinding.inflate(inflater, viewGroup, false)
            return AttractionViewHolder(binding)
        }
    }
}

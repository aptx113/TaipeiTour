package com.dante.taipeitour.feature.attractiondetails.circle

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dante.taipeitour.common.SingleFieldDiffUtils
import com.dante.taipeitour.feature.attractiondetails.AttractionDetailsViewModel

class AttractionDetailsCircleAdapter(private val viewModel: AttractionDetailsViewModel) :
    ListAdapter<String, AttractionDetailsCircleViewHolder>(SingleFieldDiffUtils { it }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttractionDetailsCircleViewHolder {
        return AttractionDetailsCircleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AttractionDetailsCircleViewHolder, position: Int) {
        holder.bind(viewModel)
    }

    override fun onViewAttachedToWindow(holder: AttractionDetailsCircleViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: AttractionDetailsCircleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }
}

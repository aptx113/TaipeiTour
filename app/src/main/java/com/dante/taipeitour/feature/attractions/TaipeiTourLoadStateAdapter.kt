package com.dante.taipeitour.feature.attractions

import androidx.paging.LoadStateAdapter

class TaipeiTourLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: androidx.paging.LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        loadState: androidx.paging.LoadState
    ): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retry)
    }
}

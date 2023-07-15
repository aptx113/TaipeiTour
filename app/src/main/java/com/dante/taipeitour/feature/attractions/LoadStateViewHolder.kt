package com.dante.taipeitour.feature.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.dante.taipeitour.R
import com.dante.taipeitour.databinding.ItemLoadStateFooterBinding

class LoadStateViewHolder(private val binding: ItemLoadStateFooterBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.txtErrorMsg.text = loadState.error.localizedMessage
        }
        binding.apply {
            footerProgress.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            txtErrorMsg.isVisible = loadState is LoadState.Error
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer, parent, false)
            val binding = ItemLoadStateFooterBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}

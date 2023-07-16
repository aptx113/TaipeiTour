package com.dante.taipeitour.feature.attractiondetails.circle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.dante.taipeitour.databinding.ItemDetailsCircleBinding
import com.dante.taipeitour.feature.attractiondetails.AttractionDetailsViewModel

class AttractionDetailsCircleViewHolder(
    private val binding: ItemDetailsCircleBinding
) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun bind(viewModel: AttractionDetailsViewModel) {
        binding.adapterPosition = absoluteAdapterPosition
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    fun markAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun markDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    companion object {
        fun create(
            viewGroup: ViewGroup
        ): AttractionDetailsCircleViewHolder {
            val layoutInflater = LayoutInflater.from(viewGroup.context)
            val binding = ItemDetailsCircleBinding.inflate(layoutInflater, viewGroup, false)
            return AttractionDetailsCircleViewHolder(binding)
        }
    }
}

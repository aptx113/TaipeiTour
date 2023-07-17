package com.dante.taipeitour.feature.attractiondetails

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dante.taipeitour.R
import com.dante.taipeitour.databinding.FragAttractionDetailsBinding
import com.dante.taipeitour.ext.launchAndRepeatWithViewLifecycle
import com.dante.taipeitour.feature.attractiondetails.circle.AttractionDetailsCircleAdapter
import com.dante.taipeitour.feature.attractiondetails.gallery.AttractionDetailsGalleryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionDetailsBinding
    private lateinit var circleAdapter: AttractionDetailsCircleAdapter
    private lateinit var galleryAdapter: AttractionDetailsGalleryAdapter
    private val viewModel by viewModels<AttractionDetailsViewModel>()
    private val args by navArgs<AttractionDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        viewDataBinding = FragAttractionDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            decoration = generateDecoration(requireContext())
        }
        args.toSavedStateHandle()
        circleAdapter = AttractionDetailsCircleAdapter(viewModel)
        galleryAdapter = AttractionDetailsGalleryAdapter()

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetailGalleryRecycler()
        viewDataBinding.detailsCirclesRecycler.adapter = circleAdapter
        viewDataBinding.officialSiteTxt.setOnClickListener {
            val action =
                AttractionDetailsFragmentDirections.toAttractionOfficialSite(
                    args.attraction.officialSite,
                    args.attraction.name
                )
            findNavController().navigate(action)
        }
        launchAndRepeatWithViewLifecycle {
            collectAttraction()
            collectSnapPosition()
        }
    }

    private fun CoroutineScope.collectAttraction() = launch {
        viewModel.attraction.collect {
            viewDataBinding.attraction = it
            if (it == null) return@collect
            val images = it.images.map { img -> img.src }
            circleAdapter.submitList(images)
            galleryAdapter.submitList(images)
        }
    }

    private fun CoroutineScope.collectSnapPosition() = launch {
        viewModel.attraction.value?.let { attraction ->
            viewDataBinding.detailsGalleryRecycler.scrollToPosition(attraction.images.size * 100)

            viewModel.snapPosition.collect {
                if (attraction.images.isEmpty()) return@collect
                viewModel.setSelectedPosition(it % attraction.images.size)
            }
        }
    }

    private fun setupDetailGalleryRecycler() {
        viewDataBinding.detailsGalleryRecycler.adapter = galleryAdapter
        val linearSnapHelper = LinearSnapHelper().apply {
            attachToRecyclerView(viewDataBinding.detailsGalleryRecycler)
        }

        viewDataBinding.detailsGalleryRecycler.setOnScrollChangeListener { _, _, _, _, _ ->
            onGalleryScrollChange(
                viewDataBinding.detailsGalleryRecycler.layoutManager, linearSnapHelper
            )
        }
    }

    private fun onGalleryScrollChange(
        layoutManager: LayoutManager?, linearSnapHelper: LinearSnapHelper
    ) {
        val snapView = linearSnapHelper.findSnapView(layoutManager) ?: return
        layoutManager?.getPosition(snapView)?.let {
            if (it != viewModel.snapPosition.value) {
                viewModel.setSnapPosition(it)
            }
        }
    }

    private fun generateDecoration(context: Context) = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.left = 0
            } else {
                outRect.left = context.resources.getDimensionPixelSize(R.dimen.space_8)
            }
        }
    }
}

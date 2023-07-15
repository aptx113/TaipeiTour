package com.dante.taipeitour.feature.attractiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dante.taipeitour.databinding.FragAttractionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttractionDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewDataBinding = FragAttractionDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }
}

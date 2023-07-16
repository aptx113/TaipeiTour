package com.dante.taipeitour.feature.attractionofficialsite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dante.taipeitour.databinding.FragAttractionOfficialSiteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttractionOfficialSiteFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionOfficialSiteBinding
    private val args by navArgs<AttractionOfficialSiteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragAttractionOfficialSiteBinding.inflate(inflater, container, false)

        viewDataBinding.officialSiteWeb.loadUrl(args.webUrl)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}

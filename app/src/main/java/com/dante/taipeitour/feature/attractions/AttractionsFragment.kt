package com.dante.taipeitour.feature.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dante.taipeitour.databinding.FragAttractionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttractionsFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        viewDataBinding = FragAttractionsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return viewDataBinding.root
    }
}

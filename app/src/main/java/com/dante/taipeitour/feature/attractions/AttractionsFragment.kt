package com.dante.taipeitour.feature.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dante.taipeitour.databinding.FragAttractionsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionsFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionsBinding
    private lateinit var pagingAdapter: AttractionAdapter
    private val viewModel by viewModels<AttractionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewDataBinding = FragAttractionsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        pagingAdapter = AttractionAdapter()

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.recycler.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.language.collectLatest { pagingAdapter.refresh() } }
                launch {
                    viewModel.attractions.collectLatest {
                        pagingAdapter.submitData(it)
                    }
                }
            }
        }
    }
}

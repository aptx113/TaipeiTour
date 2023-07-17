package com.dante.taipeitour.feature.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.dante.taipeitour.databinding.FragAttractionsBinding
import com.dante.taipeitour.ext.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionsFragment : Fragment() {

    private lateinit var viewDataBinding: FragAttractionsBinding
    private lateinit var pagingAdapter: AttractionAdapter
    private val viewModel by activityViewModels<AttractionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewDataBinding = FragAttractionsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        pagingAdapter = AttractionAdapter { viewModel.onDetailsNavigated(it) }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.recycler.adapter =
            pagingAdapter.withLoadStateFooter(TaipeiTourLoadStateAdapter { pagingAdapter.retry() })

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.selectedLanguage.collect {
                    viewModel.fetchAttractions(it)
                }
            }
            launch {
                viewModel.attractionsPagingData.collectLatest {
                    pagingAdapter.submitData(it)
                }
            }
            collectPagingLoadState()
            launch {
                viewModel.navigateToDetailsAction.collectLatest { attraction ->
                    val action =
                        AttractionsFragmentDirections.toAttractionDetails(attraction)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun CoroutineScope.collectPagingLoadState() = launch {
        pagingAdapter.loadStateFlow.collectLatest { loadStates ->
            val isListEmpty =
                loadStates.refresh is LoadState.NotLoading && pagingAdapter.itemCount == 0
            viewDataBinding.apply {
                recycler.isVisible = loadStates.source.refresh is LoadState.NotLoading
                emptyResultTxt.isVisible = isListEmpty
                progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
                retryButton.isVisible = loadStates.source.refresh is LoadState.Error
                retryButton.setOnClickListener { pagingAdapter.retry() }
            }
        }
    }

}

package com.dante.taipeitour.feature.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dante.taipeitour.data.repository.TaipeiTourRepository
import com.dante.taipeitour.model.Attraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repository: TaipeiTourRepository) :
    ViewModel() {

    val language = MutableStateFlow("en")

    private val _navigateToDetailsAction = Channel<Attraction>(Channel.CONFLATED)
    val navigateToDetailsAction = _navigateToDetailsAction.receiveAsFlow()

    val attractions = language.flatMapLatest { language ->
        repository.getAttractions(language)
    }.cachedIn(viewModelScope)

    fun onDetailsNavigated(attraction: Attraction) {
        viewModelScope.launch {
            _navigateToDetailsAction.trySend(attraction)
        }
    }
}

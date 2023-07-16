package com.dante.taipeitour.feature.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dante.taipeitour.data.repository.TaipeiTourRepository
import com.dante.taipeitour.model.Attraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repository: TaipeiTourRepository) :
    ViewModel() {

    private val _selectedLanguage = MutableStateFlow("en")
    val selectedLanguage = _selectedLanguage.asStateFlow()

    private val _attractionsPagingData =
        MutableStateFlow<PagingData<Attraction>>(PagingData.empty())
    val attractionsPagingData = _attractionsPagingData.asStateFlow()

    private val _navigateToDetailsAction = Channel<Attraction>(Channel.CONFLATED)
    val navigateToDetailsAction = _navigateToDetailsAction.receiveAsFlow()

    fun fetchAttractions(language: String) {
        viewModelScope.launch {
            repository.getAttractions(language).cachedIn(viewModelScope).collect() {
                _attractionsPagingData.value = it
            }
        }
    }


    fun onDetailsNavigated(attraction: Attraction) {
        viewModelScope.launch {
            _navigateToDetailsAction.trySend(attraction)
        }
    }

    fun onLanguageSelected(language: String) {
        _selectedLanguage.value = language
    }
}

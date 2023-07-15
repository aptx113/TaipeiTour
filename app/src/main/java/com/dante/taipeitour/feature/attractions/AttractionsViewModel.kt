package com.dante.taipeitour.feature.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dante.taipeitour.data.repository.TaipeiTourRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repository: TaipeiTourRepository) :
    ViewModel() {

    val language = MutableStateFlow("en")

    val attractions = language.flatMapLatest { language ->
        repository.getAttractions(language)
    }.cachedIn(viewModelScope)
}

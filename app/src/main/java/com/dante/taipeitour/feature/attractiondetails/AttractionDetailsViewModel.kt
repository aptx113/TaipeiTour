package com.dante.taipeitour.feature.attractiondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dante.taipeitour.common.attractionKey
import com.dante.taipeitour.model.Attraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AttractionDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val attraction: StateFlow<Attraction?> = savedStateHandle.getStateFlow(attractionKey, null)

    private val _snapPosition = MutableStateFlow(0)
    val snapPosition: StateFlow<Int> = _snapPosition

    private val _selectedPosition = MutableStateFlow(0)
    val selectedPosition: StateFlow<Int> = _selectedPosition

    fun setSnapPosition(position: Int) {
        _snapPosition.value = position
    }

    fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }
}

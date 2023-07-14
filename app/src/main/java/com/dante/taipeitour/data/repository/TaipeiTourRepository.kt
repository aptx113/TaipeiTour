package com.dante.taipeitour.data.repository

import androidx.paging.PagingData
import com.dante.taipeitour.model.Attraction
import kotlinx.coroutines.flow.Flow

interface TaipeiTourRepository {
    fun getAttractions(language: String): Flow<PagingData<Attraction>>
}

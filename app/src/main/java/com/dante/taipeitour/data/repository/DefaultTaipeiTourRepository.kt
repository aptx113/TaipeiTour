package com.dante.taipeitour.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dante.taipeitour.data.remote.TaipeiTourApi
import com.dante.taipeitour.data.remote.TaipeiTourPagingSource
import com.dante.taipeitour.model.Attraction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DefaultTaipeiTourRepository @Inject constructor(private val api: TaipeiTourApi) :
    TaipeiTourRepository {
    override fun getAttractions(language: String): Flow<PagingData<Attraction>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { TaipeiTourPagingSource(api, language) }
        ).flow
    }
}

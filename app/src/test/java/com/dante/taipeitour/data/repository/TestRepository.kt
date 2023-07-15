package com.dante.taipeitour.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import com.dante.taipeitour.model.Attraction
import com.dante.taipeitour.testing.testing.data.remote.attractionsTestData
import kotlinx.coroutines.flow.Flow

class TestRepository : TaipeiTourRepository {
    override fun getAttractions(language: String): Flow<PagingData<Attraction>> {
        val pagingSourceFactory = attractionsTestData.asPagingSourceFactory()
        return Pager(
            config = PagingConfig(pageSize = 1),
            initialKey = null,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}

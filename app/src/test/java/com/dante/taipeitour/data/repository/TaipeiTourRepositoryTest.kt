package com.dante.taipeitour.data.repository

import androidx.paging.testing.asSnapshot
import com.dante.taipeitour.data.remote.TaipeiTourApi
import com.dante.taipeitour.testing.testing.data.remote.TestApi
import com.dante.taipeitour.testing.testing.data.remote.attractionsTestData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TaipeiTourRepositoryTest {
    private lateinit var repository: TaipeiTourRepository

    private lateinit var api: TaipeiTourApi

    @Before
    fun setup() {
        api = TestApi()
        repository = DefaultTaipeiTourRepository(api)
    }

    @Test
    fun getAttractions_returnsAttractions() = runTest {
        val itemsSnapshot = repository.getAttractions("zh_tw").asSnapshot().subList(0, 3)
        assertEquals(attractionsTestData, itemsSnapshot)
    }
}

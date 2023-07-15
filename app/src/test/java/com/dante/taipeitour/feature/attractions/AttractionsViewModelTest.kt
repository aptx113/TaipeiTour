package com.dante.taipeitour.feature.attractions

import androidx.paging.testing.asSnapshot
import com.dante.taipeitour.data.repository.TaipeiTourRepository
import com.dante.taipeitour.data.repository.TestRepository
import com.dante.taipeitour.testing.rules.MainDispatcherRule
import com.dante.taipeitour.testing.testing.data.remote.attractionsTestData
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AttractionsViewModelTest {

    private lateinit var viewModel: AttractionsViewModel
    private lateinit var repository: TaipeiTourRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = TestRepository()
        viewModel = AttractionsViewModel(repository)
    }

    @Test
    fun collect_attractions_returnsPagingDataOfAttraction() = runTest {
        val snapshot = viewModel.attractions.asSnapshot()
        assert(snapshot == attractionsTestData)
    }
}

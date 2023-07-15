package com.dante.taipeitour.data.remote

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.dante.taipeitour.testing.testing.data.remote.TestApi
import com.dante.taipeitour.testing.testing.data.remote.attractionsTestData
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class TaipeiTourPagingSourceTest {

    private lateinit var pagingSource: TaipeiTourPagingSource
    private lateinit var testApi: TestApi

    @Before
    fun setup() {
        testApi = TestApi()
        pagingSource = TaipeiTourPagingSource(testApi, "en")
    }

    @Test
    fun loadsReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        val pager = TestPager(config = PagingConfig(pageSize = 3), pagingSource = pagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page

        Truth.assertThat(result.data)
            .containsExactlyElementsIn(attractionsTestData)
            .inOrder()
    }
}

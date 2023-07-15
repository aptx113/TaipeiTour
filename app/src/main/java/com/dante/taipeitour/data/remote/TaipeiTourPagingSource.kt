package com.dante.taipeitour.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dante.taipeitour.model.Attraction
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TaipeiTourPagingSource(private val api: TaipeiTourApi, private val language: String) :
    PagingSource<Int, Attraction>() {
    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getAttractions(language, nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
                nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

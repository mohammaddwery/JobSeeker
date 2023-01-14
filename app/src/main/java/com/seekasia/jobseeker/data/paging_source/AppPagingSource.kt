package com.seekasia.jobseeker.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seekasia.jobseeker.data.data_resource.remote.exception.ApiException
import com.seekasia.jobseeker.data.data_resource.remote.exception.NoConnectionException

class AppPagingSource<T: Any>(
    val fetchResults: suspend (offset: Int, limit: Int) -> List<T>,
    private val limit: Int,
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        // Start refresh at position 1 if undefined.
        val page = params.key ?: 1
        return try {
            val results = fetchResults(page, params.loadSize)
            val nextKey = if(results.isEmpty()) null else (page + 1)

            LoadResult.Page(
                data = results,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: ApiException) {
            LoadResult.Error(e)
        } catch (e: NoConnectionException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return null
    }
}
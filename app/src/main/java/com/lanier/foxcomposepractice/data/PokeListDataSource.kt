package com.lanier.foxcomposepractice.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lanier.foxcomposepractice.entity.PokemonListData
import com.lanier.foxcomposepractice.repo.PokemonListRepo
import com.lanier.libbase.utils.LogUtil

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 10:23
 * Desc  : data store
 */
class PokeListDataSource(
    private val repo: PokemonListRepo
): PagingSource<Int, PokemonListData>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListData>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListData> {
        val loadPage = params.key ?: 0
        LogUtil.i("current page = $loadPage")
        val data = repo.getListData(loadPage)
        val nextPage = if (data?.next == null) null else loadPage + 1
        return if (data != null){
            LoadResult.Page(
                data = data.results,
                prevKey = null,
                nextKey = nextPage
            )
        } else {
            LoadResult.Error(throwable = Throwable())
        }
    }
}
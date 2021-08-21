package com.lanier.foxcomposepractice.model

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.lanier.foxcomposepractice.base.BaseViewModel
import com.lanier.foxcomposepractice.data.PokeListDataSource
import com.lanier.foxcomposepractice.repo.PokemonListRepo

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 14:26
 * Desc  : list model
 */
class PokemonListModel: BaseViewModel() {

    private val repo = PokemonListRepo()

    val pager = Pager(
        PagingConfig(
            pageSize = 20, //一次加载20条
            prefetchDistance = 1,  //距离结尾处几个时候开始加载下一页
            enablePlaceholders = true
        )
    ){
        PokeListDataSource(repo)
    }.flow.cachedIn(viewModelScope)
}
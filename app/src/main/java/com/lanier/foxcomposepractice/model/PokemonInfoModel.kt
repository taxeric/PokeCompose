package com.lanier.foxcomposepractice.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.lanier.foxcomposepractice.base.BaseViewModel
import com.lanier.foxcomposepractice.entity.PokemonInfoEntity
import com.lanier.foxcomposepractice.repo.PokemonInfoRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 17:06
 * Desc  : detail info model
 */
class PokemonInfoModel: BaseViewModel() {

    private val infoRepo = PokemonInfoRepo()

    var loading by mutableStateOf(false)
    var failed by mutableStateOf(false)

    val infoFlow = MutableStateFlow(PokemonInfoEntity())

    fun getInfo(id: String) {
        viewModelScope.launch {
            loading = true
            failed = false
            val base = infoRepo.getBaseData(id)
            val detail = infoRepo.getInfoData(id)
            failed = if (base != null && detail != null) {
                infoFlow.emit(PokemonInfoEntity(base, detail))
                false
            } else {
                true
            }
            loading = false
        }
    }
}
package com.lanier.foxcomposepractice.repo

import com.lanier.foxcomposepractice.utils.RetrofitService
import com.lanier.libbase.utils.logE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 17:07
 * Desc  : info repo
 */
class PokemonInfoRepo {

    private val service = RetrofitService.service

    suspend fun getInfoData(id: String) = withContext(Dispatchers.IO){
        try {
            service.searchPokemonDetailInfo(id)
        }catch (e: Exception){
            "info data failed: ${e.message}".logE()
            null
        }
    }

    suspend fun getBaseData(id: String) = withContext(Dispatchers.IO){
        try {
            service.describeInfo(id)
        } catch (e: Exception){
            "base data failed: ${e.message}".logE()
            null
        }
    }
}
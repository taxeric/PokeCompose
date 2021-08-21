package com.lanier.foxcomposepractice.repo

import com.lanier.foxcomposepractice.utils.RetrofitService
import com.lanier.libbase.utils.LogUtil
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
            LogUtil.e("info data failed: ${e.message}")
            null
        }
    }

    suspend fun getBaseData(id: String) = withContext(Dispatchers.IO){
        try {
            service.describeInfo(id)
        } catch (e: Exception){
            LogUtil.e("base data failed: ${e.message}")
            null
        }
    }
}
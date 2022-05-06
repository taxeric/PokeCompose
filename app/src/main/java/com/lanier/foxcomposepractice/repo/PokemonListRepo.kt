package com.lanier.foxcomposepractice.repo

import com.lanier.foxcomposepractice.entity.PokemonListEntity
import com.lanier.foxcomposepractice.utils.FileUtil
import com.lanier.foxcomposepractice.utils.RetrofitService
import com.lanier.libbase.utils.logE
import com.lanier.libbase.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 10:27
 * Desc  : list repo
 */
class PokemonListRepo {

    private val service = RetrofitService.service

    suspend fun getListData(offset: Int) = withContext(Dispatchers.IO){
        try {
            val localData = FileUtil.read("${FileUtil.prefix}$offset.json")
            if (localData.isNotEmpty()){
                FileUtil.convert(localData, PokemonListEntity::class.java)
            } else {
                val index = offset * 20
                val entity = service.fetchPokemonList(offset = index)
                if (FileUtil.write("${FileUtil.prefix}$offset.json", entity)) {
                    "save success".logI()
                }
                entity
            }
        }catch (e: Exception){
            "failed: ${e.message}".logE()
            null
        }
    }
}
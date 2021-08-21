package com.lanier.foxcomposepractice.repo

import com.lanier.foxcomposepractice.entity.PokemonListEntity
import com.lanier.foxcomposepractice.utils.FileUtil
import com.lanier.foxcomposepractice.utils.RetrofitService
import com.lanier.libbase.utils.LogUtil
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
                    LogUtil.i("save success")
                }
                entity
            }
        }catch (e: Exception){
            LogUtil.e("failed: ${e.message}")
            null
        }
    }
}
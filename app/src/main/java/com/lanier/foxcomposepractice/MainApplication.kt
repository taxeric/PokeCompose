package com.lanier.foxcomposepractice

import androidx.core.content.ContextCompat
import com.lanier.foxcomposepractice.utils.FileUtil
import com.lanier.libbase.BaseApplication

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:54
 * Desc  :
 */
class MainApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        val file = ContextCompat.getExternalCacheDirs(this)
        if (file.isNotEmpty()){
            FileUtil.SAVE_PATH = file[0].path + "/"
        }
    }

    override fun baseUrl(): String = "https://pokeapi.co/api/v2/"
}
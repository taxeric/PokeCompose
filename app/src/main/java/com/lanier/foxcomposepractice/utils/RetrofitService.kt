package com.lanier.foxcomposepractice.utils

import com.lanier.foxcomposepractice.api.OpenApi
import com.lanier.libbase.net.RetrofitHelper

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 15:17
 * Desc  :
 */
object RetrofitService{

    val service = RetrofitHelper.getInstance().create(OpenApi::class.java)
}
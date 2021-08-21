package com.lanier.foxcomposepractice.base

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 15:41
 * Desc  : result
 */
sealed class ResponseResult<out A: Any>{

    data class Success<out B: Any>(val data: B): ResponseResult<B>()
    data class Error(val exception: Exception): ResponseResult<Nothing>()
}

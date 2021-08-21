package com.lanier.libbase.net

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:33
 * Desc  : 基础响应实体
 */
class HttpBaseBean<T>(
    val data: T,
    val code: Int = 0,
    val errorMsg: String = ""
)

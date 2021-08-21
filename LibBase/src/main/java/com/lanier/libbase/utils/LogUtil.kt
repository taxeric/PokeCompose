package com.lanier.libbase.utils

import android.util.Log

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:23
 * Desc  : 日志打印
 */
object LogUtil {

    const val TAG = "BaseLib-LOG"
    const val DEBUG = true

    fun i(msg: String){
        if (DEBUG){
            Log.i(TAG, msg)
        }
    }

    fun e(msg: String){
        if (DEBUG){
            Log.e(TAG, msg)
        }
    }
}
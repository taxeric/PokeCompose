package com.lanier.libbase.utils

import android.util.Log
import com.lanier.libbase.BuildConfig

/**
 * Create by Eric
 * on 2022/5/6
 */
private const val TAG = "BaseLib-LOG"
private val DEBUG = BuildConfig.DEBUG

fun String.logI(){
    if (DEBUG){
        Log.i(TAG, this)
    }
//    println(this)
}

fun String.logE(){
    if (DEBUG){
        Log.e(TAG, this)
    }
//    println(this)
}

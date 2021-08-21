package com.lanier.libbase

import android.app.Application
import com.lanier.libbase.net.RetrofitHelper

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:07
 * Desc  : empty info
 */
abstract class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitHelper.getInstance().initRetrofit(baseUrl())
    }

    abstract fun baseUrl(): String
}
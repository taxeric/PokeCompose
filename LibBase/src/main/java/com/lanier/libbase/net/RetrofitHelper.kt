package com.lanier.libbase.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:09
 * Desc  : 网络请求工具
 */
class RetrofitHelper private constructor(){

    companion object{
        private var instance: RetrofitHelper ?= null

        @JvmStatic
        fun getInstance(): RetrofitHelper{
            if (instance == null){
                synchronized(this){
                    if (instance == null){
                        instance = RetrofitHelper()
                    }
                }
            }
            return instance!!
        }
    }

    private lateinit var mRetrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    init {
        initOkHttpClient()
    }

    private fun initOkHttpClient(){
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(LogInterceptor())
            .build()
    }

    fun initRetrofit(baseUrl: String){
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(clazz: Class<T>): T = mRetrofit.create(clazz)
}
package com.lanier.libbase.net

import com.lanier.libbase.utils.logE
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.nio.charset.Charset

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 14:13
 * Desc  : 日志拦截器
 */
class LogInterceptor: Interceptor {
    private val post = "POST"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(chain.request())
        val mediaType = response.body!!.contentType()
        //结果数据
        val content = response.body!!.string()

        "Request Start--------------------------".logE()

        "请求地址: ${request.url}".logE()
        if (post == request.method) {
            if (request.body != null) {
                "请求类型: ${request.method}, ContentLength = (${request.body!!.contentLength()}-byte body)".logE()
            }
        } else {
            "请求类型 = ${request.method}".logE()
        }

        //打印请求头
        val heards = request.headers.names()
        for (names in heards) {
            for (name in request.headers(names)) {
                "请 求 头: $names = $name".logE()
            }
        }

        //如果是POST请求则输出请求参数
        if (post == request.method) {
            if (request.body != null) {
                val buffer = Buffer()
                request.body!!.writeTo(buffer)

                var charset: Charset? = Charset.forName("UTF-8")
                val contentType = request.body!!.contentType()
                if (contentType != null) {
                    charset = contentType.charset(charset)
                }
                "请求参数: ${buffer.readString(charset!!)}".logE()
            }
            "请求响应  = ${response.code} , ContentLength = (${response.body!!.contentLength()}-byte body)".logE()
        }
        "响应结果: $content".logE()
        "--------------------Request End: ".logE()
        return response.newBuilder()
            .body(ResponseBody.create(mediaType, content))
            .build()
    }
}
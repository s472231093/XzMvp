package com.example.myapplication.net

import com.safframework.log.L
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor :Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        var builder = response.newBuilder()
        val body = builder.build().body()!!.string()
//        L.header()
        L.json(body)
        return response
    }

}
package com.example.myapplication.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class Xznet {

    lateinit var service: ApiService

    private object Holder {
        val INSTANCE = Xznet()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun initNet(baseUrl:String){
        val logInterceptor = LogInterceptor()
        val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)

        val okHttpClient = okHttpClientBuilder.build()

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val retrofit = retrofitBuilder.build()
        service = retrofit.create(ApiService::class.java)

    }

}
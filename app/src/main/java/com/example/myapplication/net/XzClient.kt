package com.example.myapplication.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class XzClient {

    private object Holder {
        val INSTANCE = XzClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

//    companion object {
//        val instance: XzClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            XzClient() }
//
//    }

    fun <S>get(baseUrl: String,service:Class<S> ){
        instance.getRetrofit(baseUrl).create(service)
    }

    fun getRetrofit(baseUrl:String):Retrofit{
        return getRetrofitClient(baseUrl)
    }

    fun getRetrofitClient(baseUrl:String):Retrofit{
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val retrofit = retrofitBuilder.build()

        return retrofit
    }

    fun getOkhttpClient():OkHttpClient{
        val logInterceptor = LogInterceptor()
        val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(15000,TimeUnit.MILLISECONDS)
                .readTimeout(15000,TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)

        val okHttpClient = okHttpClientBuilder.build()

        return okHttpClient
    }

}
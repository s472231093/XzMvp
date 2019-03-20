package com.example.myapplication.net

import retrofit2.create

class Api {

    companion object {
        private lateinit var apiService: ApiService
        private val baseUrl = "http://gank.io/api/"

        fun getService(): ApiService {
//            if (apiService==null)
                apiService = XzClient.instance.getRetrofit(baseUrl).create(ApiService::class.java)
            return apiService
        }
//        fun getApi(): ApiService? {
//            if (apiService == null) {
//                synchronized(Api::class.java) {
//                    if (apiService == null) {
//                        apiService = XzClient.instance.getRetrofit(baseUrl).create(ApiService::class.java)
//                    }
//                }
//            }
//            return apiService
//        }

    }

}
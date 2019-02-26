package com.example.myapplication.net

import retrofit2.create

class Api {

    companion object {
        private var apiService: ApiService? = null

        fun getService():ApiService{
            apiService!!.let {
                XzClient.instance.getRetrofit("baseUrl").create(ApiService::class.java)
            }

            return apiService as ApiService

        }
    }


}
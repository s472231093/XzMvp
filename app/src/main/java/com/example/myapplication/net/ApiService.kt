package com.example.myapplication.net

import com.example.myapplication.model.BaseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("data/{type}/{number}/{page}")
    abstract fun getGankData(@Path("type") type: String,
                             @Path("number") pageSize: Int,
                             @Path("page") pageNum: Int): Flowable<BaseModel>
}
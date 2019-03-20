package com.example.myapplication.net

import com.example.myapplication.model.BaseModel
import com.example.myapplication.model.GankResults
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("data/{type}/{number}/{page}")
    fun getGankData(@Path("type") type: String,
                @Path("number") pageSize: Int,
                @Path("page") pageNum: Int): Deferred<GankResults>
}
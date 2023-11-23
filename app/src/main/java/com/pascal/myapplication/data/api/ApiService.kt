package com.pascal.myapplication.data.api

import com.pascal.myapplication.domain.model.ResponseMovie
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/5/lists")
    fun getMovie(@Query("language") language : String,
                 @Query("api_key") api : String,
                 @Query("page") page : Long,
                 @Query("total_result") totalResult : Int) : Single<ResponseMovie>

}
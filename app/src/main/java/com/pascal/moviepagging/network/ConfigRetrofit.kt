package com.pascal.moviepagging.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigRetrofit {

    companion object {

        fun restApi(): MovieService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttp = OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}
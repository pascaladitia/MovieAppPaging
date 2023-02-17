package com.pascal.myapplication.domain.repo

import com.pascal.myapplication.domain.model.ResponseMovie
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.Query

interface RemoteRepository {
    fun getMovie(language : String, api : String, page : Long, totalResult : Int): Single<ResponseMovie>
}
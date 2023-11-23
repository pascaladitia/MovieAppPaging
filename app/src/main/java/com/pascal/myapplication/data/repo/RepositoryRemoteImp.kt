package com.pascal.myapplication.data.repo

import com.pascal.myapplication.data.api.ApiService
import com.pascal.myapplication.domain.model.ResponseMovie
import com.pascal.myapplication.domain.repo.RemoteRepository
import io.reactivex.rxjava3.core.Single

class RepositoryRemoteImp(
    private val apiService: ApiService
): RemoteRepository {

    override fun getMovie(
        language: String,
        api: String,
        page: Long,
        totalResult: Int
    ): Single<ResponseMovie> {
        return apiService.getMovie(language, api, page, totalResult)
    }
}
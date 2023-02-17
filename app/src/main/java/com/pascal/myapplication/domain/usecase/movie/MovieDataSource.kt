package com.pascal.myapplication.domain.usecase.movie

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.pascal.myapplication.data.api.ApiService
import com.pascal.myapplication.domain.model.ResultsItem
import com.pascal.myapplication.utils.Constan.Companion.BASE_API
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDataSource (
    private val apiService: ApiService
) : PageKeyedDataSource<Long, ResultsItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ResultsItem>
    ) {

        apiService.getMovie("en-US", BASE_API,
            1, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { callback.onResult(it, null, 2) }
            }, {
                Log.e("tag error", it.message.toString())
            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {

        apiService.getMovie("en-US", BASE_API,
            params.key, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { callback.onResult(it, params.key + 2) }
            }, {
                Log.e("tag error", it.message.toString())
            })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {

    }
}
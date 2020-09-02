package com.pascal.moviepagging.dataSource

import androidx.paging.PageKeyedDataSource
import com.pascal.moviepagging.model.ResultsItem
import com.pascal.moviepagging.network.ConfigRetrofit
import com.pascal.moviepagging.network.MovieService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDataSource : PageKeyedDataSource<Long, ResultsItem>() {

    var api : MovieService

    init {
        api = ConfigRetrofit.restApi()
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ResultsItem>) {

        api.getMovie("en-US", "b64d761def5c00e40e6a36e0032741bf",
        1, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { callback.onResult(it, null, 2) }
            }, {



            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {

        api.getMovie("en-US", "b64d761def5c00e40e6a36e0032741bf",
            params.key, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { callback.onResult(it, params.key + 2) }
            }, {

            })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {

    }
}
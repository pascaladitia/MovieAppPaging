package com.pascal.moviepagging.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pascal.moviepagging.dataSource.factory.MovieDataFactory
import com.pascal.moviepagging.model.ResultsItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MovieViewModel : ViewModel() {

    var executor : Executor
    var resultItem : LiveData<PagedList<ResultsItem>>

    init {
        executor = Executors.newFixedThreadPool(5)

        var movieFactory = MovieDataFactory()

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        resultItem = LivePagedListBuilder(movieFactory, pageListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun getResults() : LiveData<PagedList<ResultsItem>> {
        return resultItem
    }
}
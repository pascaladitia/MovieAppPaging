package com.pascal.moviepagging.dataSource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pascal.moviepagging.dataSource.MovieDataSource
import com.pascal.moviepagging.model.ResultsItem

class MovieDataFactory : DataSource.Factory<Long, ResultsItem>() {

    var mutableLiveData : MutableLiveData<MovieDataSource>
    var movieDataSource : MovieDataSource

    init {
        mutableLiveData = MutableLiveData()
        movieDataSource = MovieDataSource()
    }

    //force close mas

    override fun create(): DataSource<Long, ResultsItem> {
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    fun getLiveData() : MutableLiveData<MovieDataSource> {
        return mutableLiveData
    }
}
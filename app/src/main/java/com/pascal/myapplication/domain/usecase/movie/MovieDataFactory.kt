package com.pascal.myapplication.domain.usecase.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pascal.myapplication.domain.model.ResultsItem
import javax.inject.Inject

class MovieDataFactory @Inject constructor(
    private var movieDataSource: MovieDataSource
) : DataSource.Factory<Long, ResultsItem>() {

    var mutableLiveData : MutableLiveData<MovieDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, ResultsItem> {
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}
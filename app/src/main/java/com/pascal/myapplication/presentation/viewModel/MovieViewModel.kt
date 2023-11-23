package com.pascal.myapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pascal.myapplication.domain.model.ResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
) : ViewModel() {

    @Inject
    lateinit var resultsItem: LiveData<PagedList<ResultsItem>>

    fun getResults() : LiveData<PagedList<ResultsItem>> {
        return resultsItem
    }
}
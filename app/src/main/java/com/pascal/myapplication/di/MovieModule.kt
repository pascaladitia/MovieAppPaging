package com.pascal.myapplication.di

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pascal.myapplication.data.api.ApiService
import com.pascal.myapplication.data.repo.RepositoryRemoteImp
import com.pascal.myapplication.domain.model.ResultsItem
import com.pascal.myapplication.domain.repo.RemoteRepository
import com.pascal.myapplication.domain.usecase.movie.MovieDataFactory
import com.pascal.myapplication.domain.usecase.movie.MovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Singleton
    @Provides
    fun provideRepositoryRemote(apiService: ApiService): RemoteRepository {
        return RepositoryRemoteImp(apiService)
    }

    @Singleton
    @Provides
    fun provideMovieDataSource(apiService: ApiService): MovieDataSource {
        return MovieDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideMovieDataFactory(movieDataSource: MovieDataSource): MovieDataFactory {
        return MovieDataFactory(movieDataSource)
    }

    @Singleton
    @Provides
    fun provideResultItem(movieFactory: MovieDataFactory): LiveData<PagedList<ResultsItem>> {
        var executor: Executor = Executors.newFixedThreadPool(1)

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(movieFactory, pageListConfig)
            .setFetchExecutor(executor)
            .build()
    }

}
package com.pascal.moviepagging.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.pascal.moviepagging.R
import com.pascal.moviepagging.adapter.MovieListAdapter
import com.pascal.moviepagging.model.ResponseGetData
import com.pascal.moviepagging.model.ResultsItem
import com.pascal.moviepagging.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter : MovieListAdapter? = null
    //declare view model
    private var viewModel : MovieViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieListAdapter(baseContext) {
            toDetail(it)
        }

        viewModel = CreateViewModel()
        viewModel?.getResults()

        obServer()
    }

    private fun toDetail(item: ResultsItem) {
        val intent = Intent(baseContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, item)
        startActivity(intent)
    }

    private fun obServer() {

        viewModel?.resultItem?.observe(this, Observer {
            showData(it)
        })
    }

    private fun showData(it: PagedList<ResultsItem>?) {
        adapter?.submitList(it)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    private fun CreateViewModel() = ViewModelProviders.of(this).get(MovieViewModel::class.java)
}
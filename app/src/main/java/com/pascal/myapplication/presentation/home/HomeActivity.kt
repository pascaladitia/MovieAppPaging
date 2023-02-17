package com.pascal.myapplication.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.pascal.myapplication.R
import com.pascal.myapplication.databinding.ActivityHomeBinding
import com.pascal.myapplication.domain.model.ResultsItem
import com.pascal.myapplication.presentation.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var adapter : MovieListAdapter? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieListAdapter(baseContext) {
            toDetail(it)
        }
        viewModel.getResults()

        obServer()
    }

    private fun toDetail(item: ResultsItem) {
        val intent = Intent(baseContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, item)
        startActivity(intent)
    }

    private fun obServer() {

        viewModel?.resultsItem?.observe(this, Observer {
            showData(it)
        })
    }

    private fun showData(it: PagedList<ResultsItem>?) {
        adapter?.submitList(it)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }
}
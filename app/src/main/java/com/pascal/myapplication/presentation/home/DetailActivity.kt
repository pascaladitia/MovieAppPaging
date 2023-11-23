package com.pascal.myapplication.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.pascal.myapplication.R
import com.pascal.myapplication.databinding.ActivityDetailBinding
import com.pascal.myapplication.domain.model.ResultsItem

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"

        val getDataExtra : ResultsItem? = intent.getParcelableExtra(EXTRA_DATA)
        configAdapter(getDataExtra)
    }

    private fun configAdapter(item: ResultsItem?) {
        binding.detailName.text = item?.name
        binding.detailFavorite.text = item?.favoriteCount.toString()
        binding.detailType.text = item?.listType

        val desc = item?.description
        if (desc != null) if (desc.isNotEmpty()) {
            binding.detailDeskripsi.text = item.description
        } else {
            binding.detailDeskripsi.text = "no description"
        }

        val img = item?.posterPath
        if (img != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${img}")
                .into(binding.detailImage)
        } else {
            Glide.with(this)
                .load(R.drawable.ic_baseline_broken_image_24)
                .into(binding.detailImage)
        }
    }
}
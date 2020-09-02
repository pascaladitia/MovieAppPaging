package com.pascal.moviepagging.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.pascal.moviepagging.R
import com.pascal.moviepagging.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail Movie"

        val getDataExtra : ResultsItem? = intent.getParcelableExtra(EXTRA_DATA)

        configAdapter(getDataExtra)
    }

    private fun configAdapter(item: ResultsItem?) {
        detail_name.text = item?.name
        detail_favorite.text = item?.favoriteCount.toString()
        detail_type.text = item?.listType

        val desc = item?.description
        if (desc != null) if (desc.isNotEmpty()) {
            detail_deskripsi.text = item.description
        } else {
            detail_deskripsi.text = "no description"
        }

        val img = item?.posterPath
        if (img != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${img}")
                .into(detail_image)
        } else {
            Glide.with(this)
                .load(R.drawable.ic_baseline_broken_image_24)
                .into(detail_image)
        }
    }
}
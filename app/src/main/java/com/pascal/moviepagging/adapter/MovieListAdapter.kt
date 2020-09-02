package com.pascal.moviepagging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pascal.moviepagging.R
import com.pascal.moviepagging.model.ResponseGetData
import com.pascal.moviepagging.model.ResultsItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(val context: Context, private val list: (ResultsItem) -> Unit) :
    PagedListAdapter<ResultsItem,
            RecyclerView.ViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem == oldItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieHolder) {
            holder.bindTo(getItem(position))
        }
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: ResultsItem?) {
            itemView.item_title.text = item?.name

            val desc = item?.description
            if (desc != null) if (desc.isNotEmpty()) {
                itemView.item_desc.text = item?.description
            } else {
                itemView.item_desc.text = "no description"
            }

            val img = item?.posterPath
            if (img != null) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${img}")
                    .into(itemView.item_image)
            } else {
                Glide.with(itemView)
                    .load(R.drawable.ic_baseline_broken_image_24)
                    .into(itemView.item_image)
            }

            itemView.setOnClickListener {
                if (item != null) {
                    list(item)
                }
            }
        }
    }

}

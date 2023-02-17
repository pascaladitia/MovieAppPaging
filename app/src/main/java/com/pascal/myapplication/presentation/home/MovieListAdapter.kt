package com.pascal.myapplication.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pascal.myapplication.R
import com.pascal.myapplication.databinding.ItemMovieBinding
import com.pascal.myapplication.domain.model.ResultsItem

class MovieListAdapter(val context: Context, private val list: (ResultsItem) -> Unit) :
    PagedListAdapter<ResultsItem, RecyclerView.ViewHolder>(DIFF_CALLBACK){

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
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieHolder) {
            holder.bindTo(getItem(position))
        }
    }

    inner class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ResultsItem?) {
            binding.itemTitle.text = item?.name

            val desc = item?.description
            if (desc != null) if (desc.isNotEmpty()) {
                binding.itemDesc.text = item?.description
            } else {
                binding.itemDesc.text = "no description"
            }

            val img = item?.posterPath
            if (img != null) {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/${img}")
                    .into(binding.itemImage)
            } else {
                Glide.with(binding.root)
                    .load(R.drawable.ic_baseline_broken_image_24)
                    .into(binding.itemImage)
            }

            binding.root.setOnClickListener {
                if (item != null) {
                    list(item)
                }
            }
        }
    }

}

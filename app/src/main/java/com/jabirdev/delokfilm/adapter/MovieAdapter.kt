package com.jabirdev.delokfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
import com.jabirdev.delokfilm.databinding.ItemMovieBinding
import kotlin.math.roundToInt

class MovieAdapter : PagedListAdapter<MovieEntity, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var addItemClickListener: MovieItemClickListener? = null

    inner class MovieHolder(itemView: ItemMovieBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        private val context = binding.root.context

        fun setData(data: MovieEntity){
            val transition = "${context.getString(R.string.transition_item)}_${data.title}"
            binding.root.setOnClickListener {
                addItemClickListener?.onClickItem(data,transition, binding.root)
            }

            val posterUrl = "${BuildConfig.TMDB_IMAGE_URL}${data.posterPath}"
            binding.imageView.load(posterUrl) {
                crossfade(true)
                placeholder(R.drawable.delokfilm)
                error(R.drawable.delokfilm)
            }
            binding.progressScore.progress = (data.voteAverage * 10).roundToInt()
            binding.tvTitle.text = data.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null){
            (holder as MovieHolder).setData(item)
        }
    }

    fun interface MovieItemClickListener {
        fun onClickItem(data: MovieEntity, transition: String, viewLayout: View)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
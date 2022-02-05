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
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity
import com.jabirdev.delokfilm.databinding.ItemMovieBinding
import kotlin.math.roundToInt

class TvShowAdapter : PagedListAdapter<TvEntity, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var addItemClickListener: MovieItemClickListener? = null

    inner class MovieHolder(itemView: ItemMovieBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        private val context = binding.root.context

        fun setData(data: TvEntity){
            val transition = "${context.getString(R.string.transition_item)}_${data.name}"
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
            binding.tvTitle.text = data.name
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
        fun onClickItem(data: TvEntity, transition: String, viewLayout: View)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>(){
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
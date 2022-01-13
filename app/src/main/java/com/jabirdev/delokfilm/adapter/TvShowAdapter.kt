package com.jabirdev.delokfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.data.TvEntity
import com.jabirdev.delokfilm.databinding.ItemMovieBinding
import kotlin.math.roundToInt

class TvShowAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var addItemClickListener: MovieItemClickListener? = null
    private var oldList = emptyList<TvEntity>()

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
        (holder as MovieHolder).setData(oldList[position])
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun setData(newList: List<TvEntity>){
        val diffUtil = MovieDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieDiffUtil(
        private val oldList: List<TvEntity>,
        private val newList: List<TvEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    fun interface MovieItemClickListener {
        fun onClickItem(data: TvEntity, transition: String, viewLayout: View)
    }

}
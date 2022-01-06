package com.jabirdev.delokfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.databinding.ItemMovieBinding
import com.jabirdev.delokfilm.models.DataModel
import kotlin.math.roundToInt

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var addItemClickListener: MovieItemClickListener? = null
    private var oldList = mutableListOf<DataModel.Data>()

    inner class MovieHolder(itemView: ItemMovieBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        private val context = binding.root.context

        fun setData(data: DataModel.Data){
            val transition = "${context.getString(R.string.transition_item)}_${data.title}"
            binding.root.setOnClickListener {
                addItemClickListener?.onClickItem(data,transition, binding.root)
            }

            val posterId = context.resources.getIdentifier(data.poster, "drawable", context.packageName)
            binding.imageView.load(posterId) {
                crossfade(true)
            }
            binding.progressScore.progress = (data.userScore * 10).roundToInt()
            binding.tvTitle.text = data.title
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

    fun setData(newList: MutableList<DataModel.Data>){
        val diffUtil = MovieDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieDiffUtil(
        private val oldList: List<DataModel.Data>,
        private val newList: List<DataModel.Data>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title &&
                    oldList[oldItemPosition].poster == newList[newItemPosition].poster
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    fun interface MovieItemClickListener {
        fun onClickItem(data: DataModel.Data, transition: String, viewLayout: View)
    }

}
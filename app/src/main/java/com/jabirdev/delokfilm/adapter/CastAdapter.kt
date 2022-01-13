package com.jabirdev.delokfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.data.CreditsEntity
import com.jabirdev.delokfilm.databinding.ItemCastBinding

class CastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldList = emptyList<CreditsEntity>()

    inner class CastHolder(itemView: ItemCastBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView

        fun setData(data: CreditsEntity){
            val photoUrl = "${BuildConfig.TMDB_IMAGE_URL}${data.profilePath}"
            binding.imageView.load(photoUrl){
                placeholder(R.drawable.delokfilm)
                error(R.drawable.delokfilm)
            }
            binding.tvName.text = data.name
            binding.tvCharacter.text = data.character
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CastHolder).setData(oldList[position])
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun setData(newList: List<CreditsEntity>){
        val diffUtil = CastsDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class CastsDiffUtil(
        private val oldList: List<CreditsEntity>,
        private val newList: List<CreditsEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].profilePath == newList[newItemPosition].profilePath &&
                    oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].character == newList[newItemPosition].character
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}
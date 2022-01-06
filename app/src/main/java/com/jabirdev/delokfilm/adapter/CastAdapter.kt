package com.jabirdev.delokfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.databinding.ItemCastBinding
import com.jabirdev.delokfilm.models.DataModel

class CastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldList = emptyList<DataModel.Casts>()

    inner class CastHolder(itemView: ItemCastBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView

        fun setData(data: DataModel.Casts){
            val photoUrl = "${BuildConfig.TMDB_IMAGE_URL}${data.photo}"
            binding.imageView.load(photoUrl){
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
                listener(
                    onError = { a, b ->
                        Toast.makeText(a.context, b.message, Toast.LENGTH_SHORT).show()
                    }
                )
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

    fun setData(newList: List<DataModel.Casts>){
        val diffUtil = CastsDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class CastsDiffUtil(
        private val oldList: List<DataModel.Casts>,
        private val newList: List<DataModel.Casts>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].photo == newList[newItemPosition].photo &&
                    oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].character == newList[newItemPosition].character
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}
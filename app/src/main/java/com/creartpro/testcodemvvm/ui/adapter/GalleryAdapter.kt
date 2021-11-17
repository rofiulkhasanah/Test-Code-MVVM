package com.creartpro.testcodemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creartpro.testcodemvvm.data.entities.Gallery
import com.creartpro.testcodemvvm.databinding.ItemGalleryBinding
import com.creartpro.testcodemvvm.databinding.ItemPlaceBinding

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>()  {
    val listData = mutableListOf<Gallery>()

    fun setData(data: List<Gallery>){
        listData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Gallery) {
                with(binding) {
                    Glide.with(itemView).load(data.image).into(imgItemGallery)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        listData[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listData.size
}
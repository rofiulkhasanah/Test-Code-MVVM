package com.creartpro.testcodemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creartpro.testcodemvvm.data.entities.PlaceItem
import com.creartpro.testcodemvvm.databinding.ItemPlaceBinding

class PlaceAdapter: RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>()  {
    val listPlace = mutableListOf<PlaceItem>()

    fun setData(data: List<PlaceItem>){
        listPlace.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()

    }
    inner class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(data: PlaceItem){
                with(binding){
                    Glide.with(itemView).load(data.image).into(imgItemPlace)
                    tvTitleItemPlace.text = data.title
                    tvDescriptionItemPlace.text = data.content
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        listPlace[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listPlace.size
}
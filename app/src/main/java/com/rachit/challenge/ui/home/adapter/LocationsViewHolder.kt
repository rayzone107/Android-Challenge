package com.rachit.challenge.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rachit.challenge.data.model.Location
import com.rachit.challenge.databinding.ItemLocationBinding

/**
 * Created by Rachit Goyal on 16/09/22.
 */
class LocationsViewHolder(private val binding: ItemLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        binding.apply {
            this.location = location
            executePendingBindings()
        }
    }
}

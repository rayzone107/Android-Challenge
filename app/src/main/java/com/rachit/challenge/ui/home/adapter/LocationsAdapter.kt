package com.rachit.challenge.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rachit.challenge.data.model.Location
import com.rachit.challenge.databinding.ItemLocationBinding

/**
 * Created by Rachit Goyal on 16/09/22.
 */
class LocationsAdapter(private val clickListener: LocationClickListener) : RecyclerView.Adapter<LocationsViewHolder>() {

    private var locations: MutableList<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = locations[position]

        holder.bind(location)
        holder.itemView.setOnClickListener {
            clickListener.clickOnLocation(location.id)
        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun setLocations(newLocations: List<Location>) {
        locations.clear()
        locations.addAll(newLocations)
        notifyDataSetChanged()
    }
}

interface LocationClickListener {
    fun clickOnLocation(locationId: Int)
}

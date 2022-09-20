package com.rachit.challenge.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Rachit Goyal on 20/09/22.
 */
@Serializable
data class Location(
    val id: Int,
    val address: Address,
    val description: String,
    val distance: String,
    @SerialName("location_photo") var locationPhoto: String,
    val price: Long
)
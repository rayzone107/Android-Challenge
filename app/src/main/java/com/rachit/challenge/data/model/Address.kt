package com.rachit.challenge.data.model

import kotlinx.serialization.Serializable

/**
 * Created by Rachit Goyal on 20/09/22.
 */
@Serializable
data class Address(
    val street: String,
    val city: String,
    val state: String
)
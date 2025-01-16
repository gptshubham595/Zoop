package com.zoop.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String
)

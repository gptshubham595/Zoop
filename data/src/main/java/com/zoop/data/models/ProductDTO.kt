package com.zoop.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingDTO,
    val title: String
)
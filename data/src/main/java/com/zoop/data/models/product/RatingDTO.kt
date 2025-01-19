package com.zoop.data.models.product

import kotlinx.serialization.Serializable

@Serializable
data class RatingDTO(
    val count: Int,
    val rate: Double
)
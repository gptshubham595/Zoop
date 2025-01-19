package com.zoop.data.models.categoires

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: Int,
    val image: String,
    val title: String
)
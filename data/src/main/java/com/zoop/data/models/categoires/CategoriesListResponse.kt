package com.zoop.data.models.categoires

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse(
    val data: List<CategoryDTO>,
    val msg: String,
)

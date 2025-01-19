package com.zoop.data.models.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val data: List<ProductDTO>,
    val msg: String,
)

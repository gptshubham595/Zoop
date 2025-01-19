package com.zoop.core.transformers

import com.zoop.data.models.product.ProductDTO
import com.zoop.data.models.product.ProductListResponse
import com.zoop.data.models.product.RatingDTO
import com.zoop.domain.models.product.ProductModel
import com.zoop.domain.models.product.ProductList
import com.zoop.domain.models.product.Rating

fun ProductDTO.toDomain() = ProductModel(
//    id = id,
//    title = title,
//    price = price,
//    description = description,
//    category = category,
//    image = image,
//    rating = rating.toDomain()
    categoryId = categoryId,
    description = description,
    id = id,
    image = image,
    price = price,
    title = title,
)

fun RatingDTO.toDomain() = Rating(
    rate = rate,
    count = count
)

fun ProductListResponse.toDomain() = ProductList(
    data = data.map { it.toDomain() },
    msg = msg
)
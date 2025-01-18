package com.zoop.core.transformers

import com.zoop.data.models.ProductDTO
import com.zoop.data.models.RatingDTO
import com.zoop.domain.models.Product
import com.zoop.domain.models.Rating

fun ProductDTO.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toDomain()
)

fun RatingDTO.toDomain() = Rating(
    rate = rate,
    count = count
)
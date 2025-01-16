package com.zoop.core.transformers

import com.zoop.data.models.ProductDTO
import com.zoop.domain.models.Product

fun ProductDTO.toDomain() = Product(
    id = id,
    title = this@toDomain.title,
    price = price,
    description = description,
    imageUrl = image
)
package com.zoop.domain.models

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String
)

package com.zoop.domain.models.product

data class ProductModel(
//    val id: Int,
//    val title: String,
//    val price: Double,
//    val description: String,
//    val category: String,
//    val image: String,
//    val rating: Rating
    val categoryId: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)

data class Rating(
    val rate: Double,
    val count: Int
)

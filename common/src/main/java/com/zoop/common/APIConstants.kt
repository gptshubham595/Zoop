package com.zoop.common

object APIConstants {
    const val BASE_URL =
        "http://ecommerce-ktor-4641e7ff1b63.herokuapp.com"//""https://fakestoreapi.com"
    const val PRODUCTS_URL = "$BASE_URL/products"
    const val CATEGORY_URL = "$PRODUCTS_URL/category"
//    const val CATEGORIES_URL = "$PRODUCTS_URL/categories"
    const val CATEGORIES_URL = "$BASE_URL/categories"

}
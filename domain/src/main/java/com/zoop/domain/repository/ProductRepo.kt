package com.zoop.domain.repository

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.product.ProductList

interface ProductRepo {
//    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
    suspend fun getProducts(category: Int?): ResultWrapper<ProductList>
}
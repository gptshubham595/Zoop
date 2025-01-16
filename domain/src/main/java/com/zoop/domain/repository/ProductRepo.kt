package com.zoop.domain.repository

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.Product

interface ProductRepo {
    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
}
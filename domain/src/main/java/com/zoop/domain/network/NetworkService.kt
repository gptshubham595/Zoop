package com.zoop.domain.network

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.Product

interface NetworkService {
    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
}
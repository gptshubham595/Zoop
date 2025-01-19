package com.zoop.domain.network

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.models.product.ProductList

interface NetworkService {
//    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
    suspend fun getProducts(category: Int?): ResultWrapper<ProductList>
//    suspend fun getCategories(): ResultWrapper<List<String>>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}
package com.zoop.core.repositories

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.product.ProductList
import com.zoop.domain.network.NetworkService
import com.zoop.domain.repository.ProductRepo

class ProductRepoImpl(private val networkService: NetworkService) : ProductRepo {
    //    override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
//        return networkService.getProducts(category)
//    }
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        return networkService.getProducts(category)
    }
}
package com.zoop.domain.usecases

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.product.ProductList
import com.zoop.domain.repository.ProductRepo

class GetProductsListUseCase(private val productRepository: ProductRepo) {
//    suspend operator fun invoke(category: String?): ResultWrapper<List<Product>> {
//        return productRepository.getProducts(category)
//    }
    suspend operator fun invoke(category: Int?): ResultWrapper<ProductList> {
        return productRepository.getProducts(category)
    }
}
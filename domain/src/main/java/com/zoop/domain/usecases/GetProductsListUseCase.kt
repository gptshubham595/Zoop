package com.zoop.domain.usecases

import com.zoop.domain.repository.ProductRepo

class GetProductsListUseCase(private val productRepository: ProductRepo) {
    suspend operator fun invoke(category: String?) = productRepository.getProducts(category)
}
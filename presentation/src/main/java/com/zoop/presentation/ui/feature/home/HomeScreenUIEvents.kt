package com.zoop.presentation.ui.feature.home

import com.zoop.domain.models.category.CategoriesModel
import com.zoop.domain.models.product.ProductModel

sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<ProductModel>,
        val popularProducts: List<ProductModel>,
//        val categories: List<String>
        val categories: List<CategoriesModel>
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
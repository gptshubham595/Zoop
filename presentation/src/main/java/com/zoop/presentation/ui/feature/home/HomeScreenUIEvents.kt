package com.zoop.presentation.ui.feature.home

import com.zoop.domain.models.category.CategoriesModel
import com.zoop.domain.models.product.Product

sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<Product>,
        val popularProducts: List<Product>,
//        val categories: List<String>
        val categories: List<CategoriesModel>
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
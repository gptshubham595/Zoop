package com.zoop.presentation.ui.feature.home

import com.zoop.domain.models.Product

sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(val featured: List<Product>, val popularProducts: List<Product>) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
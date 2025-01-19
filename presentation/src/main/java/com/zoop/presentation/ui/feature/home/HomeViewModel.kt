package com.zoop.presentation.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoop.common.ResultWrapper
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.models.product.ProductList
import com.zoop.domain.usecases.GetCategoriesUseCase
import com.zoop.domain.usecases.GetProductsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getProductsListUseCase: GetProductsListUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
//            val featured = getProductsList("electronics")
//            val popular = getProductsList("jewelery")
            val featured = getProductsList(1)
            val popular = getProductsList(2)
            val categories = getCategories()
            _uiState.value =
                if (featured.data.isEmpty() && popular.data.isEmpty() && categories.data.isEmpty()) {
                    HomeScreenUIEvents.Error("Error fetching products")
                } else {
                    HomeScreenUIEvents.Success(featured.data, popular.data, categories.data)
                }
        }
    }

    private suspend fun getCategories(): CategoriesListModel/*List<String>*/ {
        getCategoriesUseCase.invoke().let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return CategoriesListModel(emptyList(), "")
                }

                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }

    //    private suspend fun getProductsList(category: String?): List<Product> {
//        getProductsListUseCase.invoke(category).let { result ->
//            when (result) {
//                is ResultWrapper.Failure -> {
//                    return emptyList()
//                }
//
//                is ResultWrapper.Success -> {
//                    return result.value
//                }
//            }
//        }
//    }

    private suspend fun getProductsList(category: Int?): ProductList {
        getProductsListUseCase.invoke(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return ProductList(emptyList(), "Failed to fetch products")
                }

                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }
}


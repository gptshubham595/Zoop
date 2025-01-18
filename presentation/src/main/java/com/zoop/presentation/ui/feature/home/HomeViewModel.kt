package com.zoop.presentation.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoop.common.ResultWrapper
import com.zoop.domain.models.Product
import com.zoop.domain.usecases.GetProductsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getProductsListUseCase: GetProductsListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProductsList("electronics")
            val popular = getProductsList("jewelery")
            _uiState.value = if (featured.isNotEmpty() && popular.isNotEmpty()) {
                HomeScreenUIEvents.Success(featured, popular)
            } else {
                HomeScreenUIEvents.Error("Error fetching products")
            }
        }
    }


    private suspend fun getProductsList(category: String?): List<Product> {
        getProductsListUseCase.invoke(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }
}


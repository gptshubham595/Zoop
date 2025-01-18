package com.zoop.presentation.ui.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zoop.domain.models.Product
import com.zoop.presentation.ui.feature.product.ProductRow
import com.zoop.presentation.ui.feature.product.SearchBar
import com.zoop.presentation.ui.feature.profile.ProfileHeader
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()) {

    Scaffold { it: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val uiState = viewModel.uiState.collectAsState()

            when (val result = uiState.value) {
                HomeScreenUIEvents.Loading -> {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is HomeScreenUIEvents.Success -> {
                    val data = result
                    HomeContent(
                        featured = data.featured,
                        popular = data.popularProducts
                    )
                }

                is HomeScreenUIEvents.Error -> {
                    ErrorScreen(result)
                }
            }
        }
    }
}


@Composable
fun HomeContent(featured: List<Product>, popular: List<Product>) {
    LazyColumn {
        item {
            ProfileHeader()
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            SearchBar(value = "", onTextChange = {})
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            if (featured.isNotEmpty()) {
                ProductRow(products = featured, "Featured")
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (popular.isNotEmpty())
                ProductRow(products = popular, "Popular Products")
        }
    }
}




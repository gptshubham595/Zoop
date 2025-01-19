package com.zoop.presentation.ui.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.ImageLoader
import com.zoop.domain.models.category.CategoriesModel
import com.zoop.domain.models.product.Product
import com.zoop.presentation.ui.feature.product.ProductRow
import com.zoop.presentation.ui.feature.product.SearchBar
import com.zoop.presentation.ui.feature.profile.ProfileHeader
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
    imageLoader: ImageLoader = koinInject()
) {

    val loading = rememberSaveable { mutableStateOf(false) }
    val error = rememberSaveable { mutableStateOf<String?>(null) }

    val featureList = rememberSaveable { mutableStateOf<List<Product>>(emptyList()) }
    val productList = rememberSaveable { mutableStateOf<List<Product>>(emptyList()) }
//    val categories = rememberSaveable { mutableStateOf<List<String>>(emptyList()) }
    val categories = rememberSaveable { mutableStateOf<List<CategoriesModel>>(emptyList()) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllProducts()
    }

    val uiState = viewModel.uiState.collectAsState()

    when (val result = uiState.value) {
        HomeScreenUIEvents.Loading -> {
            loading.value = true
            error.value = null
        }

        is HomeScreenUIEvents.Success -> {
            loading.value = false
            error.value = null
            featureList.value = result.featured
            productList.value = result.popularProducts
            categories.value = result.categories
        }

        is HomeScreenUIEvents.Error -> {
            loading.value = false
            error.value = result.message
        }
    }

    HomeContent(
        featured = featureList.value,
        popular = productList.value,
        categories = categories.value,
        isLoading = loading.value,
        error = error.value,
        imageLoader
    )
}


@Composable
fun HomeContent(
    featured: List<Product>,
    popular: List<Product>,
    categories: /*List<String>*/ List<CategoriesModel>,
    isLoading: Boolean = false,
    error: String? = null,
    imageLoader: ImageLoader
) {
    when {
        isLoading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        else -> {
            LazyColumn {
                item {
                    ProfileHeader()
                    Spacer(modifier = Modifier.size(6.dp))
                    SearchBar(value = "", onTextChange = {})
                    Spacer(modifier = Modifier.size(16.dp))
                }
                item {
                    if (categories.isNotEmpty()) {
                        LazyRow {
                            items(categories, key = { it.id }) { category ->
                                val isVisible = rememberSaveable { mutableStateOf(false) }
                                LaunchedEffect(key1 = Unit) {
                                    isVisible.value = true
                                }
                                AnimatedVisibility(
                                    visible = isVisible.value,
                                    enter = fadeIn() + expandVertically()
                                ) {
                                    Text(
                                        text = category.title.replaceFirstChar { it.uppercase() },
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(MaterialTheme.colorScheme.primary)
                                            .padding(8.dp),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    if (featured.isNotEmpty()) {
                        ProductRow(products = featured, "Featured", imageLoader)
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    if (popular.isNotEmpty()) {
                        ProductRow(
                            products = popular,
                            "Popular Products",
                            imageLoader
                        )
                    }
                }
            }
        }

    }
}




package com.zoop.presentation.ui.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zoop.domain.models.Product
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    when (val result = uiState.value) {
        HomeScreenUIEvents.Loading -> {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is HomeScreenUIEvents.Success -> {
            val data: List<Product> = result.featured
            LazyColumn(
            ) {
                items(data) {
                    ProductItem(product = it)
                }
            }
        }

        is HomeScreenUIEvents.Error -> {
            ErrorScreen(result)
        }
    }
}

@Composable
fun ErrorScreen(result: HomeScreenUIEvents.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = result.message)
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${String.format(Locale.ENGLISH, "%.02f", product.price)} $",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemScreenPreview() {
    ProductItem(Product(1, "Product 1", 1.0, "Description 1", ""))
}

@Composable
fun HomeProductRow(products: List<Product>) {
    LazyColumn { }
}

@Preview
@Composable
fun HomeProductRowPreview() {
    HomeProductRow(listOf(Product(1, "Product 1", 1.0, "Description 1", "")))
}




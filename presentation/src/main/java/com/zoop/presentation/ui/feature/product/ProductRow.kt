package com.zoop.presentation.ui.feature.product

import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.zoop.domain.models.product.ProductModel


@Composable
fun ProductRow(
    products: List<ProductModel>,
    title: String,
    imageLoader: ImageLoader,
    onClick: (ProductModel) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow {
            items(products, key = { it.id }) {
                val isVisible = rememberSaveable { mutableStateOf(false) }
                LaunchedEffect(key1 = Unit) {
                    isVisible.value = true
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisible.value,
                    enter = fadeIn() + expandVertically()
                ) {
                    ProductItem(product = it, imageLoader, onClick)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeProductRowPreview() {
//    ProductRow(
//        listOf(
//            Product(
//                1,
//                "Product 1",
//                1.0,
//                "Description 1",
//                "",
//                "",
//                Rating(0.0, 0)
//            )
//        ),
//        "feature",
//        imageLoader = ImageLoader(context = LocalContext.current)
//    )
//}
//


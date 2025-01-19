package com.zoop.presentation.navigation

import com.zoop.presentation.models.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
data class ProductDetailScreen(val product: UiProductModel)
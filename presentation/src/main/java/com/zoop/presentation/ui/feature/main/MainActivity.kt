package com.zoop.presentation.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zoop.domain.models.product.ProductModel
import com.zoop.presentation.models.UiProductModel
import com.zoop.presentation.navigation.CartScreen
import com.zoop.presentation.navigation.HomeScreen
import com.zoop.presentation.navigation.ProductDetailScreen
import com.zoop.presentation.navigation.ProfileScreen
import com.zoop.presentation.navigation.productNavType
import com.zoop.presentation.ui.feature.home.HomeScreen
import com.zoop.presentation.ui.theme.ZoopTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZoopTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(navController)
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = HomeScreen) {
                            composable<HomeScreen> {
                                HomeScreen(navController)
                            }
                            composable<CartScreen> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Cart")
                                }
                            }
                            composable<ProfileScreen> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Profile")
                                }
                            }
                            composable<ProductDetailScreen>(
                                typeMap = mapOf(
                                    typeOf<UiProductModel>() to productNavType
                                )
                            ) {
                                val productRoute = it.toRoute<ProductDetailScreen>()
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Product Detail: ${productRoute.product.title}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

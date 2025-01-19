package com.zoop.presentation.ui.feature.main

import com.zoop.presentation.R
import com.zoop.presentation.navigation.CartScreen
import com.zoop.presentation.navigation.HomeScreen
import com.zoop.presentation.navigation.ProfileScreen

sealed class BottomNavItems(val route: Any, val title: String, val icon: Int) {
    data object Home : BottomNavItems(HomeScreen, "Home", R.drawable.ic_home)
    data object Profile : BottomNavItems(ProfileScreen, "Profile", R.drawable.ic_profile)
    data object Cart : BottomNavItems(CartScreen, "Cart", R.drawable.ic_cart)
}
package com.zoop.presentation.ui.feature.main

import com.zoop.presentation.R

sealed class BottomNavItems(val route: String, val title: String, val icon: Int) {
    data object Home : BottomNavItems("home", "Home", R.drawable.ic_home)
    data object Profile : BottomNavItems("profile", "Profile", R.drawable.ic_profile)
    data object Cart : BottomNavItems("cart", "Cart", R.drawable.ic_cart)
}
package com.example.homework2_network.ui.navigation

import com.example.homework2_network.util.Constants


sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Constants.Screens.HOME_SCREEN)
    data object Details : NavigationItem(Constants.Screens.DETAIL_SCREEN)
}
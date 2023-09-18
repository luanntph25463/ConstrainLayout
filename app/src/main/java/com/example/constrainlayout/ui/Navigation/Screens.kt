package com.example.constrainlayout.ui.Navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Find : Screen("find_screen")
    object Profile : Screen("profile_screen")
    object Messenger : Screen("messenger_screen")
}
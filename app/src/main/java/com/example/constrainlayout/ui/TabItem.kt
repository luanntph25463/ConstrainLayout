package com.example.constrainlayout.ui

import com.example.constrainlayout.ComposableFun
import com.example.constrainlayout.R
import com.example.constrainlayout.ui.Screens.HomeScreen
import com.example.constrainlayout.ui.Screens.FindScreens
import com.example.constrainlayout.ui.Screens.ProfileScreens

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    object Music : TabItem(R.drawable.images, "Music", { HomeScreen() })
    object Movies : TabItem(R.drawable.images, "Movies", { FindScreens() })
    object Books : TabItem(R.drawable.images, "Books", { ProfileScreens() })
}
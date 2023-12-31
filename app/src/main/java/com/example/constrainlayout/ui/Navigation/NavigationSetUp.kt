package com.example.constrainlayout.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.constrainlayout.ui.Screens.HomeScreen
import com.example.constrainlayout.ui.Screens.FindScreens
import com.example.constrainlayout.ui.Screens.MessengerScreen
import com.example.constrainlayout.ui.Screens.ProfileScreens

@Composable
fun NavigationSetup(navController: NavHostController,modifier : Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Find.route) {
            FindScreens()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreens()
        }
        composable(BottomNavItem.Messenger.route) {
            MessengerScreen()
        }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.constrainlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.constrainlayout.ui.Navigation.BottomNavigationBar
import com.example.constrainlayout.ui.Navigation.NavigationSetup
import com.example.constrainlayout.ui.Screens.HomeScreen
import com.example.constrainlayout.ui.Screens.LoginScreens
import com.example.constrainlayout.ui.User.UserModel
import com.example.constrainlayout.ui.theme.ConstrainLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val loginViewModel: UserModel by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            ConstrainLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginViewModel: UserModel = viewModel()

                    MainScreen(loginViewModel = loginViewModel)
                }
            }
        }
    }

}

@Composable
fun MainScreen(loginViewModel: UserModel) {
    if (loginViewModel.isLoggedIn) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(bottom = 20.dp)
            ) {
                NavigationSetup(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    } else {
        LoginScreens(userModel = loginViewModel)
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstrainLayoutTheme {
        HomeScreen()
    }
}

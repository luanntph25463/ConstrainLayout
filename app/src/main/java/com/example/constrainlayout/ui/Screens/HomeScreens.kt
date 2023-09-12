package com.example.constrainlayout.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.constrainlayout.ui.Post.PostScreens
@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding()) {
        PostScreens(modifier = Modifier.padding())
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.constrainlayout.ui.Navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constrainlayout.R
@Preview
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { // Tiêu đề của topBar
            Row(modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Instagram",
                    modifier = Modifier.padding()
                )
                IconButton(onClick = { /* Hành động 1 */ }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Search")
                }
            }
        },
        actions = { // Các hành động trong topBar
            IconButton(onClick = { /* Hành động 1 */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Search")
            }
            IconButton(onClick = { /* Hành động 2 */ }) {
                Icon(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = "Chat")
            }
        }
    )
}

package com.example.constrainlayout.ui.News


import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.constrainlayout.ui.News.NewsList

@Preview
@Composable
fun NewsScreens(
    modifier: Modifier = Modifier,
    NewsModel: NewsModel = viewModel()
) {
    Column(modifier = modifier) {
        NewsList(
            newsList = NewsModel.news,
        )
    }
}
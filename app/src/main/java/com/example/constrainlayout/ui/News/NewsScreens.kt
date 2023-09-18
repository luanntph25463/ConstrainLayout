package com.example.constrainlayout.ui.News


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
@Composable
fun NewsScreensMessenger(
    modifier: Modifier = Modifier,
    newsModel: NewsModel
) {
    val newsListState = remember { mutableStateOf(newsModel.news) }

    val newsList by newsListState
    Column(modifier = modifier.padding(top = 30.dp)) {
        NewsListMessenger(
            newsList = newsList,
            onRemove = { news -> newsModel.removeNewsItem(news) }
        )
    }
}
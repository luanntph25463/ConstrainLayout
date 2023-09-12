package com.example.constrainlayout.ui.News

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NewsList(
    newsList: List<News>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Thay đổi giá trị spacing tùy ý
    ) {
        items(newsList.size) { index ->
            val list = newsList[index]
            NewsItem(
                image = list.image,
                listName = list.name
            )
        }
    }
}
package com.example.constrainlayout.ui.News

import androidx.lifecycle.ViewModel
import com.example.constrainlayout.R

class NewsModel : ViewModel() {
    private val _news: MutableList<News> = getNewsList().toMutableList()
    val news: List<News>
        get() = _news
}

private fun getNewsList(): List<News> {
    val newsData = arrayOf(
        Pair(R.drawable.imag1, "Nguyễn Thành Luân"),
        Pair(R.drawable.imag2, "Lê Thị Vân Anh"),
        Pair(R.drawable.images, "Nguyễn Thi Tú Anh"),
    )

        // mapIndex vào
    return newsData.mapIndexed { index, (imageResId, taskName) ->
        News(imageResId, taskName)
    }
}
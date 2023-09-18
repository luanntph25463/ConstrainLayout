package com.example.constrainlayout.ui.News

import androidx.lifecycle.ViewModel
import com.example.constrainlayout.R

class NewsModel : ViewModel() {
    private val _news: MutableList<News> = getNewsList().toMutableList()
    val news: List<News>
        get() = _news
    fun removeNewsItem(newsItem: News) {
        _news.remove(newsItem)
    }
    private fun getNewsList(): List<News> {
        val newsData = listOf(
            News("1", R.drawable.imag1, "Nguyễn Thành Luân", "Luandzai"),
            News("2", R.drawable.imag2, "Lê Thị Vân Anh", "Luan"),
            News("3", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("4", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("5", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("6", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("7", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("8", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("9", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("10", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("11", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("12", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
            News("12", R.drawable.images, "Nguyễn Thi Tú Anh", "Luan"),
        )

        return newsData
    }
}

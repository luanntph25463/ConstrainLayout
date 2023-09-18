package com.example.constrainlayout.ui.Post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.constrainlayout.R
import com.example.constrainlayout.ui.News.News


class PostModel : ViewModel() {
    private val _post: MutableList<Post> = getPostList().toMutableList()

    private val allPosts = mutableListOf(
        Post(R.drawable.imag1, "Luan"),
        Post(R.drawable.imag2, "Lê Thị Vân Anh"),
        Post(R.drawable.images, "Nguyễn Thi Tú Anh")
    )

    private val _searchResults: MutableLiveData<List<Post>> = MutableLiveData()
    val searchResults: LiveData<List<Post>> = _searchResults

    private var searchQuery: String = ""
    val post: List<Post>
        get() = _post

    fun setSearchQuery(query: String) {
        searchQuery = query
        performSearch()
    }

    private fun performSearch() {
        val results = allPosts.filter { it.name.contains(searchQuery, ignoreCase = true) }
        _searchResults.value = results
    }
    private fun getPostList(): List<Post> {
        val postData = arrayOf(
            Pair(R.drawable.imag1, "Luan"),
            Pair(R.drawable.imag2, "Lê Thị Vân Anh"),
            Pair(R.drawable.images, "Anh"),
            Pair(R.drawable.images, "Anh"),
            Pair(R.drawable.imag2, "Anh"),
            Pair(R.drawable.imag2, "Anh"),
            Pair(R.drawable.images, "Anh"),
            Pair(R.drawable.imag1, "Anh"),
            Pair(R.drawable.imag2, "Anh"),
            Pair(R.drawable.images, "Anh"),
            Pair(R.drawable.images, "Anh"),
        )

        return postData.map { (imageResId, taskName) ->
            Post(imageResId, taskName)
        }
    }
}
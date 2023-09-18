package com.example.constrainlayout.ui.User

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserModel : ViewModel() {
    private val userList = mutableListOf<User>()

    init {
        // Initialize userList with sample data
        userList.add(User("admin", "password"))
        userList.add(User("user1", "123456"))
    }

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: Boolean get() = _isLoggedIn.value

    fun performLogin(username: String, password: String) {
        val isValid = checkCredentials(username, password)
        Log.d("TAG", "$isValid")
        _isLoggedIn.value = isValid

        if (isValid) {
            AppState.username = username
        } else {
            AppState.username = null
        }
    }


    private fun checkCredentials(username: String, password: String): Boolean {
        val user = userList.find { it.username == username && it.password == password }
        return user != null
    }

    object AppState {
        var username: String? = null
    }
}
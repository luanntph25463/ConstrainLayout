package com.example.constrainlayout.ui.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.constrainlayout.ui.Navigation.TopBar
import com.example.constrainlayout.ui.News.Messager_detail
import com.example.constrainlayout.ui.News.News
import com.example.constrainlayout.ui.News.NewsModel
import com.example.constrainlayout.ui.News.NewsScreens
import com.example.constrainlayout.ui.News.NewsScreensMessenger
import com.example.constrainlayout.ui.Post.PostModel
import com.example.constrainlayout.ui.User.UserModel

@Preview
@Composable
fun MessengerScreen() {
    ListMessages(modifier = Modifier)
}

@Composable
fun ListMessages(modifier: Modifier = Modifier ) {
    val scrollState = rememberLazyListState()
    val isTopBarVisible = remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            val newsModel = NewsModel() // Replace this with your actual NewsModel object
            NewsScreensMessenger(newsModel = newsModel)
        }
        AnimatedVisibility(
            visible = isTopBarVisible.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TopBar(
                " ${UserModel.AppState.username ?: "Guest"}",
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        // Update the visibility of AlignYourBodyRow and topBar when scrolling
        LaunchedEffect(scrollState) {
            scrollState.firstVisibleItemIndex
            scrollState.isScrollInProgress
            if (scrollState.firstVisibleItemIndex > 0) {
                isTopBarVisible.value = true
            } else if (!scrollState.isScrollInProgress) {
                isTopBarVisible.value = true
            }
        }
    }
}

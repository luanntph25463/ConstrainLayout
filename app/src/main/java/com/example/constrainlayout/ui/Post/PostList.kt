package com.example.constrainlayout.ui.Post

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.constrainlayout.ui.News.NewsScreens
import com.example.constrainlayout.ui.Navigation.TopBar


@Composable
fun PostList(
    postList: List<Post>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val isAlignYourBodyRowVisible = remember { mutableStateOf(true) }
    val isTopBarVisible = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    AnimatedVisibility(
                        visible = isAlignYourBodyRowVisible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        NewsScreens(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
                items(postList.size) { index ->
                    val list = postList[index]
                    PostItem(
                        image = list.image,
                        postName = list.name
                    )
                }
            }
        }

        // Render the topBar
        AnimatedVisibility(
            visible = isTopBarVisible.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TopBar(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        // Update the visibility of AlignYourBodyRow and topBar when scrolling
        LaunchedEffect(scrollState) {
            scrollState.firstVisibleItemIndex
            scrollState.isScrollInProgress
            if (scrollState.firstVisibleItemIndex > 0) {
                isAlignYourBodyRowVisible.value = false
                isTopBarVisible.value = true
            } else if (!scrollState.isScrollInProgress) {
                isAlignYourBodyRowVisible.value = true
                isTopBarVisible.value = true
            }
        }
    }
}
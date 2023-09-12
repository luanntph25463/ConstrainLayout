package com.example.constrainlayout.ui.Post

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun PostScreens(
    modifier: Modifier = Modifier,
    postModel: PostModel = viewModel()
) {
    Column(modifier = modifier) {
        PostList(
            postList = postModel.post
        )
    }
}
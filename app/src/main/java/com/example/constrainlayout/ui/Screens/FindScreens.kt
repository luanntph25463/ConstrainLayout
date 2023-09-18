@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.constrainlayout.ui.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.constrainlayout.ui.Post.Post
import com.example.constrainlayout.ui.Post.PostModel

@Preview
@Composable
fun FindScreens(postModel: PostModel = viewModel()) {

    Column() {
        Find(postModel = PostModel())
        listImage(postList = postModel.post, modifier = Modifier)
    }
}

@Composable
fun Find(
    postModel: PostModel,
    modifier: Modifier = Modifier
        .clip(CircleShape)
        .padding( top = 25.dp , bottom = 10.dp)
        .fillMaxWidth()
) {
    var searchQuery by remember { mutableStateOf("") }
    val ringBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFFC913B9),
                Color(0xFFF9373F),
                Color(0xFFFECD00)
            )
        )
    }
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, brush = ringBrush),
        modifier = modifier
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { value ->
                searchQuery = value
                postModel.setSearchQuery(value)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            },
            label = { Text("Search") }
        )
    }
    val searchResults by postModel.searchResults.observeAsState()
    if (!searchResults.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxSize()
        ) {
            for (result in searchResults!!) {
                Row() {
                    Image(
                        painter = painterResource(id = result.image),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = result.name)
                }
            }
        }
    }
}

@Composable
fun listImage(
    postList: List<Post>,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()
    val isTopBarVisible = remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(postList.size) { photo ->
                        val list = postList[photo]
                        Image(
                            painter = painterResource(id = list.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        AnimatedVisibility(
            visible = isTopBarVisible.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
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
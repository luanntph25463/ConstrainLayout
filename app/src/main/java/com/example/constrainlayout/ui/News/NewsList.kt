package com.example.constrainlayout.ui.News

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.constrainlayout.ui.Post.PostModel
import com.example.constrainlayout.ui.Screens.Find
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


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


@Composable
fun NewsListMessenger(
    newsList: List<News>,
    modifier: Modifier = Modifier,
    onRemove: (News) -> Unit
) {

    var skeletonLoading by rememberSaveable {
        mutableStateOf(false)
    }
    suspend fun loadingSkeleton() {
        if (!skeletonLoading) {
            skeletonLoading = true
            delay(3000L)
            skeletonLoading = false
        }
    }
    LaunchedEffect(Unit){
        loadingSkeleton()
    }
    if(skeletonLoading){
        LoadingRow()
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Thay đổi giá trị spacing tùy ý
    ) {
        item {
            Find(postModel = PostModel())
        }
        item{
            NewsScreens(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Row {
                Text(text = "Tin Nhắn")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Tin Nhắn Đang Chờ")
            }
        }
        items(newsList.size) { index ->
            val list = newsList[index]
            Messager_detail(
                image = list.image,
                listName = list.name,
                description = list.description
            ) { onRemove(list) }
        }
    }

}
@Composable
private fun LoadingRow() {
    // Creates an `InfiniteTransition` that runs infinite child animation values.
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        // `infiniteRepeatable` repeats the specified duration-based `AnimationSpec` infinitely.
        animationSpec = infiniteRepeatable(
            // The `keyframes` animates the value by specifying multiple timestamps.
            animation = keyframes {
                // One iteration is 1000 milliseconds.
                durationMillis = 1000
                // 0.7f at the middle of an iteration.
                0.7f at 500
            },
            // When the value finishes animating from 0f to 1f, it repeats by reversing the
            // animation direction.
            repeatMode = RepeatMode.Reverse
        )
    )
    Row(
        modifier = Modifier
            .heightIn(min = 64.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = alpha))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(Color.LightGray.copy(alpha = alpha))
        )
    }
}

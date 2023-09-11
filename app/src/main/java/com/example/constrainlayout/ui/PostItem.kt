@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.constrainlayout.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.constrainlayout.R
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun PostItem(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 7.dp)
    ) {
        val (
            imgAvatar,
            imgMore,
            textUserNameTop,
            imgPost,
            imgLike,
            imgComment,
            imgShare,
            imgBookmark,
            textLikes,
            textUserNameBottom,
            textViewComments,
            imgTags
        ) = createRefs()

        val ringBrush = remember {
            Brush.sweepGradient(
                listOf(
                    Color(0xFFC913B9),
                    Color(0xFFF9373F),
                    Color(0xFFFECD00)
                )
            )
        }

        val borderWidth = 1.5.dp

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "user avatar",
            modifier = modifier
                .constrainAs(imgAvatar) {
                    start.linkTo(parent.start, 14.dp)
                    top.linkTo(parent.top)
                }
                .size(32.dp)
                .border(
                    border = BorderStroke(borderWidth, ringBrush),
                    shape = CircleShape
                )
                .padding(borderWidth + (borderWidth / 2))
                .clip(CircleShape)

        )

        Column(
            modifier = modifier
                .constrainAs(textUserNameTop) {
                    start.linkTo(imgAvatar.end, 7.dp)
                    top.linkTo(imgAvatar.top)
                    bottom.linkTo(imgAvatar.bottom)
                }
        ) {
            Text(
                text = "Ruffles",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )

            Text(
                text = "Sponsored",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp
                )
            )
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "more",
            modifier = modifier
                .constrainAs(imgMore) {
                    end.linkTo(parent.end, 14.dp)
                    top.linkTo(imgAvatar.top)
                    bottom.linkTo(imgAvatar.bottom)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "post image",
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .constrainAs(imgPost) {
                    start.linkTo(parent.start)
                    top.linkTo(imgAvatar.bottom, 7.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop
        )


        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "like or unlike",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgLike) {
                    top.linkTo(imgPost.bottom, 7.dp)
                    start.linkTo(imgAvatar.start)
                }
        )

        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "comment",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgComment) {
                    top.linkTo(imgLike.top)
                    start.linkTo(imgLike.end, 10.dp)
                }
        )

        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "share",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgShare) {
                    top.linkTo(imgComment.top)
                    start.linkTo(imgComment.end, 10.dp)
                }
        )

        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "share",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgBookmark) {
                    top.linkTo(imgLike.top)
                    end.linkTo(parent.end, 14.dp)
                }
        )

        Text(
            text = "100 Likes",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            modifier = modifier
                .constrainAs(textLikes) {
                    start.linkTo(imgLike.start)
                    top.linkTo(imgLike.bottom, 7.dp)
                }
        )

        Text(
            text = "Username Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt... more ",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = modifier
                .constrainAs(textUserNameBottom) {
                    start.linkTo(textLikes.start)
                    top.linkTo(textLikes.bottom, 7.dp)
                    end.linkTo(parent.end, 14.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "View all 16 comments",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            modifier = modifier
                .constrainAs(textViewComments) {
                    start.linkTo(textUserNameBottom.start)
                    top.linkTo(textUserNameBottom.bottom, 7.dp)
                }
        )

    }
}


private val alignYourBodyData = listOf(
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
    R.drawable.ic_launcher_background to R.string.app_name,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    val ringBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFFC913B9),
                Color(0xFFF9373F),
                Color(0xFFFECD00)
            )
        )
    }

    val borderWidth = 3.dp
    ConstraintLayout(modifier = modifier.padding(30.dp)) {
        val (image, textContent) = createRefs()

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape) // Bo tròn hình ảnh
                .border(
                    border = BorderStroke(width = borderWidth, brush = ringBrush),
                    shape = CircleShape
                )
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(textContent.top, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = stringResource(text),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .paddingFromBaseline(top = 5.dp, bottom = 24.dp)
                .constrainAs(textContent) {
                    top.linkTo(image.bottom, margin = 5.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Preview
@Composable
fun list(modifier: Modifier = Modifier) {
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
                        AlignYourBodyRow(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
                items(20) { index ->
                    PostItem()
                    Spacer(modifier.padding(bottom = 20.dp))
                }
            }
        }

        // Update the visibility of AlignYourBodyRow and topBar when scrolling
        LaunchedEffect(scrollState) {
            scrollState.firstVisibleItemIndex
            scrollState.firstVisibleItemScrollOffset
            scrollState.isScrollInProgress
            if (scrollState.firstVisibleItemIndex > 0) {
                isAlignYourBodyRowVisible.value = false
                isTopBarVisible.value = true
            } else if (!scrollState.isScrollInProgress) {
                isAlignYourBodyRowVisible.value = true
                isTopBarVisible.value = true
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
    }
}
@Composable
fun home(modifier : Modifier = Modifier){
        Scaffold(
            bottomBar = { ScootheBottomNavigation() }
        ) { padding ->
            list(Modifier.padding(padding))
        }

}
@Composable
fun TopBar(modifier : Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { // Tiêu đề của topBar
            Text(
                text = "Instagram",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        actions = { // Các hành động trong topBar
            IconButton(onClick = { /* Hành động 1 */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Search")
            }
            IconButton(onClick = { /* Hành động 2 */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )
}
@Composable
fun PostList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(10) { post ->
            PostItem()
            Spacer(modifier.padding(bottom = 20.dp))
        }
    }
}
@Composable
// tạo 1 thanh bottomNavigation
fun ScootheBottomNavigation(modifier:Modifier = Modifier){
    NavigationBar(modifier = modifier,
    containerColor =  MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "home"
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Profile"
                )
            },
            selected = false,
            onClick = {}
        )
    }
}
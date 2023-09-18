@file:OptIn(

)

package com.example.constrainlayout.ui.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.constrainlayout.R
import com.example.constrainlayout.ui.Navigation.TopBar
import com.example.constrainlayout.ui.News.News
import com.example.constrainlayout.ui.News.NewsItemProfile
import com.example.constrainlayout.ui.News.NewsModel
import com.example.constrainlayout.ui.News.NewsScreensMessenger
import com.example.constrainlayout.ui.Post.Post
import com.example.constrainlayout.ui.Post.PostModel
import com.example.constrainlayout.ui.User.UserModel

@Preview
@Composable
fun ProfileScreens(postModel: PostModel = viewModel()) {

    profile(postList = postModel.post, modifier = Modifier)
}

@Composable
fun profile(
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
            NameProfile()
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

@Composable
fun NameProfile(
    modifier: Modifier = Modifier.background(color = Color.White),
    NewsModel: NewsModel = viewModel()
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
    LazyColumn {
        // Các composable sẽ được hiển thị trong màn hình cuộn
        item {
            Column() {
                Row(modifier = modifier) {
                    ConstraintLayout(modifier = modifier.padding(20.dp)) {
                        val imageRef = createRef()
                        val textContentRef = createRef()
                        Image(
                            painter = painterResource(R.drawable.imag1),
                            contentDescription = null,
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape) // Rounded image
                                .border(
                                    border = BorderStroke(width = borderWidth, brush = ringBrush),
                                    shape = CircleShape
                                )
                                .constrainAs(imageRef) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(textContentRef.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = "Luan",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .paddingFromBaseline(top = 5.dp, bottom = 12.dp)
                                .constrainAs(textContentRef) {
                                    top.linkTo(imageRef.bottom, margin = 5.dp)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                    ConstraintLayout(modifier = modifier.padding(8.dp)) {
                        val TextShare = createRef()
                        val TextShare2 = createRef()
                        Text(
                            text = "1",
                            modifier = Modifier
                                .constrainAs(TextShare) {
                                    top.linkTo(parent.top, margin = 32.dp)
                                    bottom.linkTo(TextShare2.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = "Đăng",
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .paddingFromBaseline(top = 5.dp, bottom = 12.dp)
                                .constrainAs(TextShare2) {
                                    top.linkTo(TextShare.bottom, margin = 5.dp)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                    ConstraintLayout(modifier = modifier.padding(7.dp)) {
                        val TextShare3 = createRef()
                        val TextShare4 = createRef()
                        Text(
                            text = "46",
                            modifier = Modifier
                                .constrainAs(TextShare3) {
                                    top.linkTo(parent.top, margin = 32.dp)
                                    bottom.linkTo(TextShare4.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = "Người Theo Dõi",
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .paddingFromBaseline(top = 5.dp, bottom = 12.dp)
                                .constrainAs(TextShare4) {
                                    top.linkTo(TextShare3.bottom, margin = 5.dp)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                    ConstraintLayout(modifier = modifier.padding(8.dp)) {
                        val TextShare5 = createRef()
                        val TextShare6 = createRef()
                        Text(
                            text = "18",
                            modifier = Modifier
                                .constrainAs(TextShare5) {
                                    top.linkTo(parent.top, margin = 30.dp)
                                    bottom.linkTo(TextShare6.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = "Đang Theo Dõi",
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .paddingFromBaseline(top = 5.dp, bottom = 12.dp)
                                .constrainAs(TextShare6) {
                                    top.linkTo(TextShare5.bottom, margin = 5.dp)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }

                }
                Row(modifier = modifier) {
                    ConstraintLayout(modifier = modifier.padding(20.dp)) {
                        val button1 = createRef()
                        val button2 = createRef()
                        val button3 = createRef()

                        Button(
                            onClick = { /* Xử lý sự kiện của button 1 */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = MaterialTheme.colorScheme.surface
                            ),
                            modifier = Modifier.constrainAs(button1) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            }
                        ) {
                            Text(
                                text = "Chỉnh Sửa",
                                color = Color.Black
                            )
                        }

                        Button(
                            onClick = { /* Xử lý sự kiện của button */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = MaterialTheme.colorScheme.surface
                            ),
                            modifier = Modifier.constrainAs(button2) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(button1.end, margin = 10.dp)
                            }
                        ) {
                            Text(
                                text = "Thông Tin Người Dùng",
                                color = Color.Black // Đặt màu chữ thành màu trắng
                            )
                        }

                        OutlinedButton(
                            onClick = { /* Xử lý sự kiện của button 3 */ },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(1.dp, Color.LightGray),
                            modifier = Modifier
                                .constrainAs(button3) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(button2.end, margin = 8.dp)
                                },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.Black // Đặt màu sắc cho icon
                            )
                        }

                        createHorizontalChain(
                            button1,
                            button2,
                            button3,
                            chainStyle = ChainStyle.Packed
                        )
                    }
                }
            }
        }
        item {
            Row(modifier = modifier) {
                Column(modifier = modifier) {
                    NewsListprofile(
                        newsList = NewsModel.news,
                    )
                }
            }
        }

        item {
            Row(modifier = modifier) {
                Column(modifier = modifier) {
                    TabLayoutApp()
                }
            }
        }
        item {
            Row(modifier = modifier) {
                Column(modifier = modifier.padding(top = 100.dp)) {
                    NewsListprofile(
                        newsList = NewsModel.news,
                    )
                }
            }
        }
    }
}

@Composable
fun NewsListprofile(
    newsList: List<News>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Thay đổi giá trị spacing tùy ý
    ) {
        items(newsList.size) { index ->
            val list = newsList[index]
            NewsItemProfile(
                image = list.image,
                listName = list.name
            )
        }
    }
}

@Composable
fun TabLayoutApp() {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    val tabs = listOf(
        Icons.Default.Home,
        Icons.Default.Favorite,
        Icons.Default.Settings
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Nội dung của từng tab
        when (selectedTabIndex) {
            0 -> ContentTab1()
            1 -> ContentTab2()
            2 -> ContentTab3()
        }

        // TabLayout
        TabRow(selectedTabIndex) {
            tabs.forEachIndexed { index, icon ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    icon = { Icon(icon, contentDescription = null) }
                )
            }
        }
    }
}

@Composable
fun ContentTab1(  NewsModel: NewsModel = viewModel()) {
    Text(text = "Content for Tab 1")

}

@Composable
fun ContentTab2() {
    Text(text = "Content for Tab 2")
}

@Composable
fun ContentTab3(  NewsModel: NewsModel = viewModel()) {
    Text(text = "Content for Tab 3")

}
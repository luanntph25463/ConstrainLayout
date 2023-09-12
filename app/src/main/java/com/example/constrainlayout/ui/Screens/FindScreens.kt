package com.example.constrainlayout.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.constrainlayout.ui.Post.Post
import com.example.constrainlayout.ui.Post.PostModel

@Composable
fun FindScreens(){

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier.clip(CircleShape),
    onSearch: (String) -> Unit
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
    var searchQuery by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, brush = ringBrush),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchText ->
                    searchQuery = searchText
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                ),
                placeholder = {
                    Text(text = "Tìm Kiếm")
                },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { onSearch(searchQuery) },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Tìm kiếm")
            }
        }
    }
}

@Composable
fun SearchScreen() {
    val postModel = viewModel<PostModel>()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            onSearch = { searchText ->
                postModel.setSearchQuery(searchText)
                postModel.performSearch()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Search Results:")
        Spacer(modifier = Modifier.height(8.dp))

        val searchResults by postModel.searchResults.observeAsState(emptyList())

        LazyColumn {
            items(searchResults) { post ->
                ListItem(post)
            }
        }
    }
}

@Composable
fun ListItem(post: Post) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.image),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = post.name)
    }
}
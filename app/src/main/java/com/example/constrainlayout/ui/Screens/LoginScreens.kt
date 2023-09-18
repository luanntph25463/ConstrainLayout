@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.constrainlayout.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.constrainlayout.MainScreen
import com.example.constrainlayout.R
import com.example.constrainlayout.ui.User.UserModel


@Composable
fun LoginScreens(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    userModel: UserModel
) {
    // không nên khởi tạo userModel trong fun sẽ gọi lại liên tục làm login lỗi
    login(modifier = modifier, userModel)
}

@Composable
fun login(modifier: Modifier = Modifier, userModel: UserModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxHeight()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.instagram),
                contentDescription = null,
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chào Mừng tới Instagram",
                fontFamily = FontFamily.SansSerif,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Red
            )
        }
        val ringBrush = remember {
            Brush.sweepGradient(
                listOf(
                    Color(0xFFC913B9),
                    Color(0xFFF9373F),
                    Color(0xFFFECD00)
                )
            )
        }
        Row(modifier = Modifier.padding(50.dp)){

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape,
                border = BorderStroke(width = 2.dp, brush = ringBrush),
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    label = { androidx.compose.material3.Text("userName")
                    },

                    )
            }
        }
        Row(modifier = Modifier.padding(top = 20.dp ,start =50.dp)) {

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape,
                border = BorderStroke(width = 2.dp, brush = ringBrush),
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    label = { androidx.compose.material3.Text("password") },
                )
            }
        }
        Row(modifier = Modifier.padding(top = 20.dp ,start =50.dp)){
            Button(
                onClick = { userModel.performLogin(username, password) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.padding(top = 5.dp,start = 100.dp,end = 100.dp)
            ) {
                Text("Login",
                    color = Color.White)
            }
        }

    }
}


@Preview
@Composable
fun PreviewLogin() {
    val loginViewModel: UserModel = viewModel()

    MainScreen(loginViewModel = loginViewModel)
}
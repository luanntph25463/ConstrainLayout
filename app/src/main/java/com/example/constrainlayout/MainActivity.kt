package com.example.constrainlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.example.constrainlayout.ui.PostItem
import com.example.constrainlayout.ui.PostList
import com.example.constrainlayout.ui.home
import com.example.constrainlayout.ui.list
import com.example.constrainlayout.ui.theme.ConstrainLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstrainLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    home()
                }
            }
        }
    }
}

//@Composable
//fun MyScreen() {
//    ConstraintLayout {
//        // Định nghĩa các thành phần giao diện và ràng buộc
//        val (button1, button2, button3) = createRefs()
//
//        Button(
//            onClick = { /* Xử lý sự kiện khi nhấn vào nút */ },
//            modifier = Modifier.constrainAs(button1) {
//                top.linkTo(parent.top, margin = 20.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//        ) {
//            // Nội dung của nút
//        }
//
//        Button(
//            onClick = { /* Xử lý sự kiện khi nhấn vào nút */ },
//            modifier = Modifier.constrainAs(button2) {
//                top.linkTo(button1.bottom, margin = 32.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//        ) {
//            // Nội dung của nút
//        }
//
//        Button(
//            onClick = { /* Xử lý sự kiện khi nhấn vào nút */ },
//            modifier = Modifier.constrainAs(button3) {
//                top.linkTo(button2.top)
//                bottom.linkTo(button2.bottom)
//                start.linkTo(button2.end, margin = 16.dp)
//            }
//        ) {
//            // Nội dung của nút
//        }
//    }
//}

//@Composable
//fun MyScreen() {
//    ConstraintLayout {
//        val (button1, button2, button3) = createRefs()
//
//        Button(
//            onClick = { /* Handle button1 click */ },
//            modifier = Modifier.constrainAs(button1) {
//                top.linkTo(parent.top, margin = 16.dp)
//                start.linkTo(parent.start, margin = 50.dp)
//            }
//        ) {
//            Text("Button 1")
//        }
//
//        Button(
//            onClick = { /* Handle button2 click */ },
//            modifier = Modifier.constrainAs(button2) {
//                top.linkTo(parent.top, margin = 16.dp)
//                start.linkTo(button1.end, margin = 16.dp)
//                end.linkTo(button3.start, margin = 16.dp)
//            }
//        ) {
//            Text("Button 2")
//        }
//
//        Button(
//            onClick = { /* Handle button3 click */ },
//            modifier = Modifier.constrainAs(button3) {
//                top.linkTo(parent.top, margin = 16.dp)
//                end.linkTo(parent.end, margin = 16.dp)
//            }
//        ) {
//            Text("Button 3")
//        }
//
//        createHorizontalChain(button1, button2, button3)
//    }
//}
//@Composable
//fun MyScreen() {
//    ConstraintLayout {
//        val (button1, button2) = createRefs()
//
//        Button(
//            onClick = { /* Handle button1 click */ },
//            modifier = Modifier.constrainAs(button1) {
//                top.linkTo(parent.top, margin = 16.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//        ) {
//            Text("Button 1")
//        }
//
//        Button(
//            onClick = { /* Handle button2 click */ },
//            modifier = Modifier.constrainAs(button2) {
//                top.linkTo(button1.bottom, margin = 16.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//        ) {
//            Text("Button 2")
//        }
//    }
//}
//@Composable
//fun DecoupledConstraintLayout() {
//    BoxWithConstraints {
//        // khởi tạo constrains
//        // if màn hình nhỏ tự động căn chỉnh margin
//        val constraints = if (minWidth < 600.dp) {
//            // gọi  decoupledConstraints truyền margin vào
//            decoupledConstraints(margin = 16.dp) // Portrait constraints
//        } else {
//            decoupledConstraints(margin = 32.dp) // Landscape constraints
//        }
//
//        ConstraintLayout(constraints) {
//            Button(
//                onClick = { /* Do something */ },
//                modifier = Modifier.layoutId("button")
//            ) {
//                Text("Button")
//            }
//
//            Text("Text", Modifier.layoutId("text"))
//        }
//    }
//}

//
//// build function  margin sẵn
//private fun decoupledConstraints(margin: Dp): ConstraintSet {
//    return ConstraintSet {
//        // khởi tạo với id
//        val button = createRefFor("button")
//        val text = createRefFor("text")
//        // căn chỉnh
//        constrain(button) {
//            top.linkTo(parent.top, margin = margin)
//        }
//        constrain(text) {
//            top.linkTo(button.bottom, margin)
//        }
//    }
//}
//@Composable
//fun MyScreen() {
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        val (button, text) = createRefs()
//
//        val startGuideline = createGuidelineFromStart(0.3f)
//        val topGuideline = createGuidelineFromTop(10.dp)
//
//        Button(
//            onClick = { /* Handle button click */ },
//            modifier = Modifier.constrainAs(button) {
//                top.linkTo(topGuideline)
//                start.linkTo(startGuideline)
//            }
//        ) {
//            Text("Button")
//        }
//
//        Text(
//            text = "Hello, World!",
//            modifier = Modifier.constrainAs(text) {
//                top.linkTo(button.bottom, margin = 16.dp)
//                start.linkTo(startGuideline)
//            }
//        )
//    }
//}
//
//@Composable
//fun BarrierExample() {
//    ConstraintLayout(modifier = Modifier
//        .fillMaxWidth()
//        .padding(12.dp)) {
//        val (input1, input2, input3) = createRefs()
//        val barrier = createEndBarrier(input1, input2)
//        Text(text = "Iuput 1 Large", modifier = Modifier.constrainAs(input1) {
//            top.linkTo(parent.top, 8.dp)
//            start.linkTo(parent.start, 8.dp)
//        } )
//        Text(text = "Iuput 2", modifier = Modifier.constrainAs(input2) {
//            top.linkTo(input1.bottom, 8.dp)
//            start.linkTo(parent.start, 8.dp)
//        } )
//        Text(text = "Iuput 3", modifier = Modifier.constrainAs(input3) {
//            top.linkTo(input1.bottom, 8.dp)
//            start.linkTo(barrier, 8.dp)
//        } )
//    }
//}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstrainLayoutTheme {
        home()
    }
}
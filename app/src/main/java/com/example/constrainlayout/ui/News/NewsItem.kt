package com.example.constrainlayout.ui.News

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun NewsItem(
    @DrawableRes image: Int,
    listName: String,
    modifier: Modifier = Modifier.background(Color.White)
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
        val imageRef = createRef()
        val textContentRef = createRef()

        Image(
            painter = painterResource(image),
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
            text = listName,
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
}
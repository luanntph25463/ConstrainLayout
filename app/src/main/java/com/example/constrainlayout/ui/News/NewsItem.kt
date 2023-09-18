package com.example.constrainlayout.ui.News

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

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
@Composable
fun NewsItemProfile(
    @DrawableRes image: Int,
    listName: String,
    modifier: Modifier = Modifier.background(Color.White)
) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            .border(
                border = BorderStroke(width = 2.dp, color = Color.LightGray)
            )
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
            val textContentRef1 = createRef()
            val button1 = createRef()

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
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(textContentRef.top, margin = 120.dp) // Increased margin
                    }
            )
            Spacer(Modifier.padding(top = 30.dp))
            Text(
                text = listName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(top = 20.dp, bottom = 12.dp)
                    .constrainAs(textContentRef) {
                        top.linkTo(imageRef.bottom, margin = 60.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "Có Nguyễn T.Luân Theo Dõi",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(top = 5.dp, bottom = 12.dp)
                    .constrainAs(textContentRef1) {
                        top.linkTo(textContentRef.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Button(
                onClick = { /* Xử lý sự kiện của button 1 */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.constrainAs(button1) {
                    top.linkTo(textContentRef1.bottom, margin = 16.dp)
                    start.linkTo(parent.start,margin = 30.dp)
                }
            ) {
                Text(
                    text = "Theo Dõi",
                    color = Color.White
                )
            }

            createVerticalChain(
                imageRef, textContentRef, textContentRef1, button1,
                 chainStyle = ChainStyle.SpreadInside,
            )
        }
    }
}
@Composable
fun Messager_detail(
    @DrawableRes image: Int,
    listName: String,
    description: String,
    modifier: Modifier = Modifier.background(Color.White).fillMaxWidth(),
    onRemove: () -> Unit
){

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
    val swipeModifier = Modifier.swipeToDismiss(onDismissed = onRemove)
    ConstraintLayout(modifier = modifier.padding(5.dp).then(swipeModifier)) {
        val imageRef = createRef()
        val textLine1Ref = createRef()
        val textLine2Ref = createRef()
        val button1 = createRef()

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
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = listName,
            modifier = Modifier.constrainAs(textLine1Ref) {
                top.linkTo(parent.top, margin = 15.dp)
                start.linkTo(imageRef.end, margin = 15.dp)
            }
        )

        Text(
            text = description,
            modifier = Modifier.constrainAs(textLine2Ref) {
                top.linkTo(textLine1Ref.bottom, margin = 4.dp)
                start.linkTo(imageRef.end, margin = 15.dp)
                bottom.linkTo(parent.bottom)
            }
        )

        IconButton(
            onClick = onRemove, // Gọi hàm xóa khi được nhấp vào
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(imageRef.end, margin = 250.dp)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete, // Hoặc sử dụng biểu tượng xóa khác
                contentDescription = "Delete",
                tint = Color.Black // Đặt màu sắc cho icon
            )
        }
    }
}
private fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    // This Animatable stores the horizontal offset for the element.
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                // Interrupt any ongoing animation.
                offsetX.stop()
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        // Record the position after offset
                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        launch {
                            // Overwrite the Animatable value while the element is dragged.
                            offsetX.snapTo(horizontalDragOffset)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocity = velocityTracker.calculateVelocity().x
                // Calculate where the element eventually settles after the fling animation.
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                // The animation should end as soon as it reaches these bounds.
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        // Not enough velocity; Slide back to the default position.
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        // Enough velocity to slide away the element to the edge.
                        offsetX.animateDecay(velocity, decay)
                        // The element was swiped away.
                        onDismissed()
                    }
                }
            }
        }
    }
        // Apply the horizontal offset to the element.
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}
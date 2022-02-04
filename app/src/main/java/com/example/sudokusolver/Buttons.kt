package com.example.sudokusolver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun Buttons(onPress: (Int) -> Unit) {
    Column {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp))

        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            Row {
                ConstraintLayout {
                    val (box1, box2) = createRefs()
                    Box(modifier = Modifier.constrainAs(box1){
                        top.linkTo(parent.top)
                    }) {

                        LazyColumn {
                            items(3) { x ->
                                LazyRow {
                                    items(3) { y ->
                                        NumButton((x * 3 + y + 1).toString()) {
                                            onPress(x * 3 + y + 1)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Box(modifier = Modifier
                        .constrainAs(box2){
                            top.linkTo(parent.top)
                            bottom.linkTo(box1.bottom)
                            start.linkTo(box1.end)
                        },
                        contentAlignment = Alignment.Center) {
                        NumButton {
                            onPress(0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NumButton(num: String = "<", onPress: () -> Unit) {
    Box(modifier = Modifier
        .size(60.dp)
        //.padding(2.dp)
        .border(1.dp, Color.Black)
        .background(Color(150, 180, 255))
        .clickable(
            onClick = onPress
        ),
        contentAlignment = Alignment.Center) {
        Text(num
            , fontSize = 30.sp)
    }
}
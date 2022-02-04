package com.example.sudokusolver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun Node(num: Int = 0, highLight: Boolean = false, onPress: () -> Unit) {
    var bColor by remember { mutableStateOf(Color.White) }

    bColor = if(highLight) Color.Gray
    else Color.White

    Box(modifier = Modifier
        .size(43.dp)
        .border(1.dp, Color.Black)
        .background(bColor)
        .clickable(
            onClick = onPress
        ),
        contentAlignment = Alignment.Center) {
        Text(if(num == 0) "" else num.toString()
            , fontSize = 30.sp)
    }
}
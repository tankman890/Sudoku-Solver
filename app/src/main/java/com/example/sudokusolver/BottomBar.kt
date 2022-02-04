package com.example.sudokusolver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBar(onPress: () -> Unit, onReset: () -> Unit, textState: Int = 0) {
    Column {
        Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))
        var text by remember { mutableStateOf("") }
        var textColor by remember { mutableStateOf(Color.White) }

        if(textState == 0) {
            text = ""
        } else if(textState == 1) {
            text = "Solution Found!"
            textColor = Color(56, 201, 59)
        } else {
            text = "A Solution does not exist"
            textColor = Color.Red
        }

        Box(modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center) {
            Text(text, fontWeight = FontWeight.Bold, color = textColor)
        }

        Row {
            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .background(Color(150, 180, 255))
                .border(1.dp, Color.Black)
                .clickable (
                           onClick = onPress
                ),
            contentAlignment = Alignment.Center) {
                Text("Find Solution", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(31, 31, 31))
                .border(1.dp, Color.Black)
                .clickable(
                    onClick = onReset
                ),
                contentAlignment = Alignment.Center) {
                Text("Reset", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = Color(150, 180, 255))
            }
        }
    }
}
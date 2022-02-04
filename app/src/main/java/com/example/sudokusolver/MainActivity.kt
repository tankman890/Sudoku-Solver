package com.example.sudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sudokusolver.ui.theme.SudokuSolverTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

var NODE_HIGHLIGHTED = false
var xHIGHLIGHTED = 0
var yHIGHLIGHTED = 0

@Composable
fun App() {
    val arrVal = remember { mutableStateListOf<Int>() }
    val highLightVal = remember { mutableStateListOf<Boolean>() }
    var textState by remember { mutableStateOf(0) }

    arrVal.addAll(List(81) { 0 })
    highLightVal.addAll(List(81) { false })

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(219, 233, 255))
    ) {
        Box(modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center) {
            Text(text = "Sudoku Solver", fontSize = 30.sp
                , fontWeight = FontWeight.Bold)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            LazyColumn {
                items(9) { x ->
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        items(9) { y ->
                            Node(arrVal[x * 9 + y], highLightVal[x * 9 + y]) {
                                highLightVal[x * 9 + y] = !highLightVal[x * 9 + y]

                                if(NODE_HIGHLIGHTED) {
                                    highLightVal[xHIGHLIGHTED * 9 + yHIGHLIGHTED] = false

                                    NODE_HIGHLIGHTED = false
                                }

                                if(highLightVal[x * 9 + y]) {
                                    NODE_HIGHLIGHTED = true
                                    xHIGHLIGHTED = x
                                    yHIGHLIGHTED = y
                                }
                            }
                        }
                    }
                }
            }
        }

        Buttons {
            if(NODE_HIGHLIGHTED) {
                arrVal[xHIGHLIGHTED * 9 + yHIGHLIGHTED] = it
            }
        }

        BottomBar ({
            solve(arrVal)
            if (found) {
                for(i in 0..8) {
                    for(j in 0..8) {
                        arrVal[i * 9 + j] = grid[i][j]
                    }
                }

                textState = 1
            } else {
                textState = 2
            }
        }, {
            for(i in 0..80) arrVal[i] = 0
            textState = 0
        },
        textState)
    }
}

@Preview
@Composable
fun ComposablePreview(){
    App()
}
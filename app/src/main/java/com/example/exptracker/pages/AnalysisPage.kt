package com.example.exptracker.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.exptracker.R
import com.example.exptracker.component.SelectMonth
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.ExpTrackerTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.exptracker.data.dummyTx

@Composable
fun AnalysisPage() {
    Column(Modifier.fillMaxWidth()) {
        Box() {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
//                    changeRoute(Screen.AnalysisScreen.route)
                }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "forward arrow for financial report",
                        tint = Color.Black
                    )
                }
                Text(text = "Analysis", fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.width(25.dp))
            }
        }
        DonutGraph()

    }
}

@Composable
fun DonutGraph() {
    val selected = remember { mutableStateOf(false) }
    Box(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectMonth(month = CustomMonth.Oct, changeMonth = {})
                Box(
                    Modifier
                        .fillMaxWidth()
//                        .height(120.dp)
                        .padding(start = 120.dp)
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { selected.value = !selected.value },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (selected.value) MaterialTheme.colors.primary else Color.White,
                            ),
                            shape = RoundedCornerShape(
                                topEnd = 0.dp,
                                topStart = 15.dp,
                                bottomStart = 15.dp,
                                bottomEnd = 0.dp
                            )
                        ) {
                            Modifier.weight(1f)
                            Icon(
                                painter = painterResource(id = R.drawable.graph_icon),
                                contentDescription = "graph",
                                Modifier.height(30.dp),
                                tint = if (selected.value) Color.White else MaterialTheme.colors.primary

                            )
                        }

                        Button(
                            onClick = { selected.value = !selected.value },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (selected.value) Color.White else MaterialTheme.colors.primary,
                            ),
                            shape = RoundedCornerShape(
                                topEnd = 15.dp,
                                topStart = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 15.dp
                            )

                        ) {
                            Modifier.weight(1f)
                            Icon(
                                painter = painterResource(id = R.drawable.pirchart_icon),
                                contentDescription = "chart",
                                Modifier.height(30.dp),
                                tint = if (selected.value) MaterialTheme.colors.primary else Color.White
                            )
                        }

                    }
                }
            }
            Box(Modifier.fillMaxWidth()) {
                if (selected.value) GraphOfData() else Piechart()
            }

        }
    }
}

@Composable
fun GraphOfData() {
    Box() {
        Text(text = "Graphhhh")
    }
}

@Composable
fun Piechart(
    values: List<Float> = listOf(65f, 40f, 25f, 20f),
    colors: List<Color> = listOf(
        Color(0xFFFF6384), Color(0xFFFFCE56), Color(0xFF36A2EB), Color(0xFF448AFF)
    ),
    legend: List<String> = listOf("Mango", "Banana", "Apple", "Melon"),
    size: Dp = 200.dp,
    thickness: Dp = 36.dp
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val items = dummyTx


        val sumOfValues = values.sum()


        val proportions = values.map {
            it * 100 / sumOfValues
        }


        val sweepAngles = proportions.map {
            360 * it / 100
        }

        Canvas(
            modifier = Modifier.size(size = size)
        ) {

            var startAngle = -90f

            for (i in values.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = false,
                    style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round)
                )
                startAngle += sweepAngles[i]
            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        Column() {
            for (i in values.indices) {
                DisplayLegend(color = colors[i], legend = legend[i])
            }
        }
    }
}


@Composable
fun DisplayLegend(color: Color, legend: String) {
    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend, color = Color.Black
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun analysis() {
    ExpTrackerTheme {
        AnalysisPage()
    }
}
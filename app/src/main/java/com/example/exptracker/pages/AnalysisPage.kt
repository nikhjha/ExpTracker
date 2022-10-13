package com.example.exptracker.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.exptracker.ui.theme.ExpTrackerTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.sp
import com.example.exptracker.component.BarGraph
import com.example.exptracker.component.LineGraph
import com.example.exptracker.data.*
import com.example.exptracker.util.CurrencyFormater
import kotlin.random.Random

@Composable
fun AnalysisPage(tx: List<Transaction>, month: CustomMonth, updateMonth: (CustomMonth) -> Unit) {
    if (tx.isEmpty()) {
        NoTransactionPanel(month, updateMonth)
        return
    }
    val sorts: List<Sort> = listOf(
        Sort("byLessAmount", "Increasing Amount"),
        Sort("byMoreAmount", "Decreasing Amount"),
    )
    var sortBy by remember { mutableStateOf(sorts[1]) }
    val group = tx.groupBy { t ->
        t.category
    }
    val sortedGroup = if (sortBy == sorts[0]) group.toList().sortedBy {
        it.second.fold(0f) { prev, curr ->
            prev + curr.amount
        }
    } else group.toList().sortedByDescending {
        it.second.fold(0f) { prev, curr ->
            prev + curr.amount
        }
    }
    var values: MutableList<Float> = mutableListOf()
    var colors: MutableList<Color> = mutableListOf()
    sortedGroup.forEach {
        values.add(it.second.fold(0f) { prev, curr ->
            prev + curr.amount
        })
        colors.add(it.first.color.primary)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Analysis", fontWeight = FontWeight.Bold, color = Color.Black)
        }
        DonutGraph(values, colors, tx[0].currency, month, updateMonth)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)
            var expanded by remember { mutableStateOf(false) }
            Box {
                OutlinedButton(
                    onClick = { expanded = !expanded },
                    border = BorderStroke(1.dp, color = Color.Gray),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(0f)),
                    contentPadding = PaddingValues(end = 8.dp, start = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "DropDown",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sorts.forEach {
                        DropdownMenuItem(
                            onClick = {
                                sortBy = it
                                expanded = false
                            },
                            modifier = Modifier.background(color = if (it == sortBy) Color.LightGray else Color.White)
                        ) {
                            Text(it.title)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        StatesBar(group = sortedGroup, values.sum())
    }
}

@Composable
fun NoTransactionPanel(month: CustomMonth, updateMonth: (CustomMonth) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Analysis", fontWeight = FontWeight.Bold, color = Color.Black)
        }
        DonutGraph(month = month, updateMonth = updateMonth)
        Text(
            "No transaction available. Please add expense.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )

    }
}

@Composable
fun StatesBar(group: List<Pair<Category, List<Transaction>>>, total: Float) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        group.forEach {
            StatusBar(category = it.first, amount = it.second.fold(0f) { prev, curr ->
                prev + curr.amount
            }, total = total, currency = it.second[0].currency)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun StatusBar(category: Category, amount: Float, total: Float, currency: Currency) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(24.dp))
                    .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    Modifier
                        .size(20.dp)
                        .background(color = category.color.primary, CircleShape)
                )
                Text(text = category.title)
            }
            Text(
                "-${CurrencyFormater(amount, currency)}",
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(amount / total)
                    .height(20.dp)
                    .background(color = category.color.primary, shape = RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
fun DonutGraph(
    values: List<Float> = listOf(),
    colors: List<Color> = listOf(),
    currency: Currency = Currencies[0],
    month: CustomMonth,
    updateMonth: (CustomMonth) -> Unit
) {
    val selected = remember { mutableStateOf(true) }
    Box(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectMonth(month = month, changeMonth = updateMonth)
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
            if(values.isNotEmpty()) {
                Box(Modifier.fillMaxWidth()) {
                    if (selected.value) GraphOfData(values, colors) else Piechart(
                        values,
                        colors,
                        currency = currency
                    )
                }
            }
        }
    }
}

@Composable
fun GraphOfData(
    values: List<Float>, colors: List<Color>,
) {
    Box(modifier = Modifier.padding(top = 32.dp)) {

        BarGraph(data = values, max_value = values.maxOf { it.toInt() }, colors = colors)
    }
}

@Composable
fun Piechart(
    values: List<Float> = listOf(65f, 40f, 25f, 20f),
    colors: List<Color> = listOf(
        Color(0xFFFF6384), Color(0xFFFFCE56), Color(0xFF36A2EB), Color(0xFF448AFF)
    ),
    size: Dp = 200.dp,
    thickness: Dp = 36.dp,
    currency: Currency
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        val sumOfValues = values.sum()


        val proportions = values.map {
            it * 100 / sumOfValues
        }


        val sweepAngles = proportions.map {
            360 * it / 100
        }
        Box(Modifier.size(size)) {
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
            Box(Modifier.size(size)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${CurrencyFormater(sumOfValues, currency)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Analysis() {
    ExpTrackerTheme {
        AnalysisPage(dummyTx, CustomMonth.Oct, {})
    }
}
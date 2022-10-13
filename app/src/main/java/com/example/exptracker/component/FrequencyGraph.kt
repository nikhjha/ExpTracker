package com.example.exptracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exptracker.data.*
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.groupByCumulativeSelection
import com.example.exptracker.util.groupByRecentSelection
import kotlin.random.Random

@Composable
fun CustomButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) CardColor.Orange.backgroundColor else Color.White.copy(
                0f
            )
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = if (selected) CardColor.Orange.primary else Color.Gray
        )
    }
}

@Composable
fun RecentSelector(selectedBase: RecentSelection, changeBase: (base: RecentSelection) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(text = RecentSelection.Today.title, RecentSelection.Today == selectedBase) {
            changeBase(RecentSelection.Today)
        }
        CustomButton(text = RecentSelection.Week.title, RecentSelection.Week == selectedBase) {
            changeBase(RecentSelection.Week)
        }
        CustomButton(text = RecentSelection.Month.title, RecentSelection.Month == selectedBase) {
            changeBase(RecentSelection.Month)
        }
        CustomButton(text = RecentSelection.Year.title, RecentSelection.Year == selectedBase) {
            changeBase(RecentSelection.Year)
        }
    }
}

@Composable
fun Graph(tx: List<Transaction>) {
    val list =
        if (tx.size > 1) listOf(nullTx, *tx.toTypedArray()) else if (tx.size == 1) listOf(nullTx, tx[0]) else listOf(nullTx, nullTx)
    val yStep = list.maxOf {
        it.amount
    }
    val height = 150
    val multiplier = if(yStep == 0f) 0f else height.toFloat() / yStep
    /* to test with random points */
    val points = list.map {
        if(multiplier > 0f) return@map (it.amount) * (height / yStep)  + 5f
        (it.amount) * multiplier + 5f
    }

    LineGraph(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp).height((height).dp),
        xValues = (1..list.size).map { it - 1  },
        yValues = (0..list.size).map { (it) * (yStep / (list.size + 1)).toInt() },
        points = points,
        verticalStep = height / (list.size + 2),
        0.dp
    )
}

@Composable
fun FrequencyGraph(tx: List<Transaction>) {
    var selectedBase by remember { mutableStateOf<RecentSelection>(RecentSelection.Today) }
    val changeBase: (base: RecentSelection) -> Unit = {
        selectedBase = it
    }
    val group = groupByCumulativeSelection(tx)
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Graph(group[selectedBase]?.reversed() ?: listOf())
        Spacer(modifier = Modifier.height(16.dp))
        RecentSelector(selectedBase, changeBase)
    }
}

@Preview(showBackground = true)
@Composable
fun FrequencyPreview() {
    ExpTrackerTheme {
        FrequencyGraph(tx = dummyTx)
    }
}
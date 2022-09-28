package com.example.exptracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.component.FrequencyGraph
import com.example.exptracker.component.NotificationBar
import com.example.exptracker.component.RecentTxPanel
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.CurrencyFormater


@Composable
fun HomePage() {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 64.dp, 64.dp))
                .background(color = Color(0xFFFFF6E5))
                .padding(bottom = 32.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NotificationBar()
                Spacer(Modifier.height(16.dp))
                Text("Current Expenses", color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = CurrencyFormater(9400f), fontSize = 32.sp)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Total Budget : ${CurrencyFormater(10000f)}",
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = CardColor.Orange.primary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .padding(12.dp)

                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
        FrequencyGraph()
        RecentTxPanel()
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ExpTrackerTheme {
        HomePage()
    }
}
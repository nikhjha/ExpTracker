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
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.data.Transaction
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.CurrencyFormater


@Composable
fun HomePage(
    changeRoute: (String) -> Unit,
    changeNavRoute: (String) -> Unit,
    tx: List<Transaction>,
    budget: Float,
    currency: Currency,
    month: CustomMonth,
    updateMonth: (CustomMonth) -> Unit,
    deleteTx : (String) -> Unit = {}
) {
    val total = tx.fold(0f) { prev, curr ->
        prev + curr.amount
    }
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
                .background(color = if (total > budget) Color(240, 120, 120) else Color(0xFFFFF6E5))
                .padding(bottom = 32.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NotificationBar(
                    month = month,
                    updateMonth = updateMonth,
                    changeRoute = changeRoute
                )
                Spacer(Modifier.height(16.dp))
                Text("Current Expenses", color = if (total > budget) Color.White else Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = CurrencyFormater(total, currency), fontSize = 32.sp)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Total Budget : ${CurrencyFormater(budget, currency)}",
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
        FrequencyGraph(tx)
        RecentTxPanel(changeRoute, changeNavRoute, tx, deleteTx)
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ExpTrackerTheme {
        HomePage({}, {}, listOf(), 0f, Currencies[0], CustomMonth.Oct, {})
    }
}
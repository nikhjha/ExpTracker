package com.example.exptracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.component.SelectMonth
import com.example.exptracker.component.TransactionCard
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.data.Transaction
import com.example.exptracker.data.dummyTx
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.groupByCumulativeSelection
import com.example.exptracker.util.groupByRecentSelection

@Composable
fun TransactionsPage(
    changeRoute : (String) -> Unit,
    changeNavRoute : (String) -> Unit,
    tx : List<Transaction>,
    month: CustomMonth,
    updateMonth: (CustomMonth) -> Unit,
    deleteTx : (String) -> Unit = {}
) {
    val items = groupByRecentSelection(tx)
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        SelectMonth(month = month, changeMonth = updateMonth)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    color = CardColor.Purple.backgroundColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 16.dp).clickable {
                    changeRoute(Screen.AnalysisScreen.route)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "See your financial report", color = CardColor.Purple.primary)
            IconButton(onClick = {
                changeRoute(Screen.AnalysisScreen.route)
            }) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "forward arrow for financial report",
                    tint = CardColor.Purple.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(tx.isEmpty()){
            Text(
                "No transaction available. Please add expense.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        }
        LazyColumn {
            items.forEach {
                if (it.value.isNotEmpty()) {
                    item {
                        Text(
                            text = it.key.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
                it.value.forEach {
                    item {
                        Box(Modifier.padding(bottom = 16.dp)) {
                            TransactionCard(tx = it, changeNavRoute, deleteTx)
                        }
                    }
                }
            }
            item{
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsPagePreview() {
    ExpTrackerTheme {
        TransactionsPage({},{}, listOf(), CustomMonth.Oct,{})
    }
}
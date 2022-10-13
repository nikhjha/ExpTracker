package com.example.exptracker.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.data.Transaction
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme


@Composable
fun RecentTxPanel(changeRoute: (String) -> Unit, changeNavRoute : (String) -> Unit, tx : List<Transaction>, deleteTx : (String) -> Unit = {}) {
    val items = if(tx.size > 4) tx.slice(IntRange(0, 4)) else tx
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Recent Transaction", fontWeight = FontWeight.Bold)
            Button(
                onClick = {
                    changeRoute(Screen.TransactionsScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = CardColor.Purple.backgroundColor
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "See All",
                    fontWeight = FontWeight.Bold,
                    color = CardColor.Purple.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items.forEach {
                TransactionCard(tx = it, changeNavRoute, deleteTx)
            }
        }
        if (items.isEmpty()) {
            Text(
                "No recent transaction available. Please add expense.",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecentTxPanelPreview() {
    ExpTrackerTheme {
        RecentTxPanel({},{}, listOf<Transaction>())
    }
}

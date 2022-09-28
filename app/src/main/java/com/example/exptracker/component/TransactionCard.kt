package com.example.exptracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.data.Transaction
import com.example.exptracker.data.dummyTx
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.CurrencyFormater
import com.example.exptracker.util.encapsulateText
import com.example.exptracker.util.toTime

@Composable
fun TransactionCard(tx: Transaction) {
    Card(
        modifier = Modifier.height(100.dp),
        backgroundColor = Color(250, 250, 250),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .background(
                            color = tx.category.color.backgroundColor,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .padding(16.dp)

                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = tx.category.image),
                        contentDescription = null,
                        tint = tx.category.color.primary,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(top = 4.dp, bottom = 4.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(tx.category.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(encapsulateText(tx.description), color = Color.Gray, fontSize = 16.sp)
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(top = 4.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    "-${CurrencyFormater(tx.amount)}",
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(toTime(tx.dateTime), color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionCardPreview() {
    ExpTrackerTheme {
        Column(Modifier.padding(16.dp)) {
            TransactionCard(tx = dummyTx[0])
        }
    }
}
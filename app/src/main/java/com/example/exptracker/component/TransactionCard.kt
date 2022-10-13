package com.example.exptracker.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.util.CurrencyFormater
import com.example.exptracker.util.encapsulateText
import com.example.exptracker.util.toFullTime
import com.example.exptracker.util.toTime

@Composable
fun TransactionCard(tx: Transaction, changeNavRoute: (String) -> Unit, deleteTx : (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .clickable {
                expanded = !expanded
            },
        backgroundColor = Color(250, 250, 250),
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .height(84.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
                            .padding(top = 4.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(tx.category.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(encapsulateText(tx.description), color = Color.Gray, fontSize = 16.sp)
                    }
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(top = 4.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "-${CurrencyFormater(tx.amount, tx.currency)}",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(toTime(tx.dateTime), color = Color.Gray)
                }
            }
            Box(Modifier.animateContentSize()){
                if (expanded) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            text = toFullTime(tx.dateTime),
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tx.description,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Button(
                                onClick = {
                                    changeNavRoute(Screen.EditTxScreen.route + "/${tx.id}")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = tx.category.color.backgroundColor
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(text = "Edit", fontSize = 18.sp)
                            }
                            Button(
                                onClick = {deleteTx(tx.id)},
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color.Red
                                ),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(text = "Delete", fontSize = 18.sp)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionCardPreview() {
    ExpTrackerTheme {
        Column(Modifier.padding(16.dp)) {
            TransactionCard(tx = dummyTx[0], {})
        }
    }
}
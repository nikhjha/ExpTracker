package com.example.exptracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.data.Category
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.data.getAllCategory
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensePanel(
    popRoute: () -> Unit,
    title: String,
    txAmount: Float,
    txCategory: Category,
    txDescription: String,
    btnText: String,
    onBtnClick: (Float, Category, String) -> Unit,
    currency: Currency
) {
    var amount by remember { mutableStateOf("${if(txAmount == 0f) "" else txAmount}") }
    var expanded by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf(txCategory) }
    val options = getAllCategory()
    var description by remember { mutableStateOf(txDescription) }
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(CardColor.Blue.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                popRoute()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "forward arrow for financial report",
                    tint = Color.White
                )
            }
            Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.width(25.dp))
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("How Much ?", color = Color.Gray, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currency.sign,
                    color = Color.White,
                    fontSize = 64.sp
                )
                TextField(value = amount, onValueChange = { amount = it },
                    placeholder = {
                                  Text(
                                      "0", fontSize = 64.sp, color = Color.LightGray
                                  )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 64.sp),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White.copy(0f),
                        textColor = Color.White
                    ),
                    modifier = Modifier.width(250.dp)
                )
            }
        }
        Box(
            Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
                )
                .padding(32.dp)
        ) {
            Column {
                ExposedDropdownMenuBox(expanded = expanded,
                    onExpandedChange = { expanded = !expanded }) {
                    OutlinedTextField(
                        readOnly = true,
                        value = category.title,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.primary,
                            unfocusedBorderColor = Color.Gray,
                            backgroundColor = Color.White
                        ),
                        shape = RoundedCornerShape(15.dp),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    category = selectionOption
                                    expanded = false
                                }
                            ) {
                                Text(
                                    text = selectionOption.title,
                                    Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = description,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { description = it },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        unfocusedBorderColor = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    placeholder = {
                        Text(text = "Description", color = Color.Gray)
                    }
                )
                Spacer(modifier = Modifier.height(72.dp))
                Button(
                    onClick = {
                        onBtnClick(if(amount == "") 0f else amount.toFloat(), category, description)
                    },
                    Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                ) {
                    Text(text = btnText, fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensePanelPreview() {
    ExpTrackerTheme {
        ExpensePanel(popRoute = {}, "Add", 0f, Category.Shopping, "", "Add", { _, _, _ ->
            run {}
        }, Currencies[0])
    }
}
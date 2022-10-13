package com.example.exptracker.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.viewmodels.UserDetailViewModel


@Composable
fun SetupPage(
    navController: NavHostController,
    userDetailViewModel: UserDetailViewModel = UserDetailViewModel()
) {
    val options = Currencies
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var budget by remember {
        mutableStateOf("")
    }
    Box(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp)) {
            Text(
                "Let’s setup your \n" + "account!", style = MaterialTheme.typography.h3
            )
            Text(
                "Just Provide some information so it will be\n" + " easy to  maintain",
                Modifier.padding(top = 30.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            FormInfo(
                budget,
                selectedOptionText,
                { budget = it },
                { selectedOptionText = it },
                options
            )
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Button(
                        onClick = {
                            userDetailViewModel.updateBudget(if(budget == "") 0f else budget.toFloat())
                            userDetailViewModel.updateCurrency(selectedOptionText)
                            navController.navigate(Screen.MainAppScreen.route)
                        },
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp), shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = "Let's go")
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormInfo(
    budget: String,
    selectedOptionText: Currency,
    changeBudget: (String) -> Unit,
    changeOptionText: (Currency) -> Unit,
    options: List<Currency>
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Box {
        Column(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Text("Enter Your Budget", Modifier.padding(start = 10.dp))
                    OutlinedTextField(
                        value = budget,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { newText -> changeBudget(newText) },
                        label = { Text(text = "Budget") },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.primary,
                            unfocusedBorderColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text("Select Your Currency", Modifier.padding(start = 10.dp))
                    ExposedDropdownMenuBox(expanded = expanded,
                        onExpandedChange = { expanded = !expanded }) {
                        OutlinedTextField(
                            readOnly = true,
                            value = selectedOptionText.sign,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {},
                            label = { Text(text = "Currency") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colors.primary,
                                unfocusedBorderColor = Color.Gray,
                                backgroundColor = Color.White
                            ),
                            shape = RoundedCornerShape(15.dp),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
//                                    colors = ExposedDropdownMenuDefaults.textFieldColors()
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
                                        changeOptionText(selectionOption)
                                        expanded = false
                                    }
                                ) {
                                    Text(
                                        text = "${selectionOption.name} (${selectionOption.sign})",
                                        Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SetupPagePreview() {
    ExpTrackerTheme {
        SetupPage(rememberNavController())
    }
}
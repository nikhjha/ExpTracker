package com.example.exptracker.pages

import androidx.compose.runtime.Composable
import com.example.exptracker.component.ExpensePanel
import com.example.exptracker.data.Category

@Composable
fun AddTxPage(){
    ExpensePanel(changeRoute = {}, "Add Expense", 0f, Category.Shopping, "", "Add", {
        a,b,c -> {}
    })
}
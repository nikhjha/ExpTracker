package com.example.exptracker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.exptracker.component.ExpensePanel
import com.example.exptracker.data.Category
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.data.Transaction
import com.example.exptracker.viewmodels.TransactionViewModel
import com.example.exptracker.viewmodels.UserDetailViewModel
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun AddTxPage(
    popRoute: () -> Unit,
    transactionViewModel: TransactionViewModel,
    userDetailViewModel: UserDetailViewModel
) {
    val currency = userDetailViewModel.currency.collectAsState(initial = Currencies[0])
    ExpensePanel(
        popRoute, "Add Expense", 0f, Category.Shopping, "", "Add", { a, b, c ->
            transactionViewModel.addTx(
                Transaction(
                    UUID.randomUUID().toString(),
                    a,
                    c,
                    b,
                    LocalDateTime.now(),
                    currency.value ?: Currencies[0]
                )
            )
            popRoute()
        },
        currency.value ?: Currencies[0]
    )
}
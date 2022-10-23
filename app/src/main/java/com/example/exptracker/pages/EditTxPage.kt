package com.example.exptracker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.exptracker.component.ExpensePanel
import com.example.exptracker.data.Category
import com.example.exptracker.data.Transaction
import com.example.exptracker.navigation.Screen
import com.example.exptracker.viewmodels.TransactionViewModel
import com.example.exptracker.viewmodels.UserDetailViewModel

@Composable
fun EditTxPage(
    popRoute: () -> Unit,
    txId: String,
    transactionViewModel: TransactionViewModel,
) {
    val tx = transactionViewModel.txs.collectAsState()
    val transaction: Transaction? = tx.value.find { it ->
        it.id == txId
    }
    if (transaction == null) {
        popRoute()
    }
    ExpensePanel(
        popRoute,
        "Edit Expense",
        transaction?.amount ?: 0f,
        transaction?.category ?: Category.Shopping,
        transaction?.description ?: "",
        "save",
     { a, b, c ->
        transactionViewModel.changeTx(
            tx.value.find {
                it.id == txId
            }?.copy(
                amount = a,
                description = c,
                category = b
            ))
         popRoute()
    }, currency = tx.value[0].currency)
}
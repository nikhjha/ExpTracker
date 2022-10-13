package com.example.exptracker.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.exptracker.component.BottomBar
import com.example.exptracker.data.Currency
import com.example.exptracker.data.Transaction
import com.example.exptracker.data.getAllMonths
import com.example.exptracker.navigation.Screen
import com.example.exptracker.viewmodels.TransactionViewModel
import com.example.exptracker.viewmodels.UserDetailViewModel


@Composable
fun MainApp(
    navController: NavHostController,
    route: String,
    changeRoute: (String) -> Unit,
    transactionViewModel: TransactionViewModel = TransactionViewModel(),
    userDetailViewModel: UserDetailViewModel = UserDetailViewModel()
) {
    val txs = transactionViewModel.txs.collectAsState()
    val budget = userDetailViewModel.budget.collectAsState()
    val currency = userDetailViewModel.currency.collectAsState()
    val selectedMonth = userDetailViewModel.selectedMonth.collectAsState()
    val tx = txs.value.filter {
        it.currency == currency.value && selectedMonth.value == getAllMonths()[it.dateTime.monthValue - 1]
    }
    val changeNavRoute: (String) -> Unit = {
        navController.navigate(it)
    }
    Scaffold(
        bottomBar = { BottomBar(currentRoute = route, changeRoute, changeNavRoute) },
//        floatingActionButton = {
//            Box() {
//                FloatingActionButton(onClick = {}) {
//                    Icon(Icons.Filled.Add, "")
//                }
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(Modifier.padding(top = if (route == Screen.HomeScreen.route) 0.dp else 16.dp)) {
            when (route) {
                Screen.AnalysisScreen.route -> AnalysisPage(
                    tx,
                    selectedMonth.value
                ) { userDetailViewModel.updateMonth(it) }
                Screen.HomeScreen.route -> HomePage(
                    changeRoute,
                    changeNavRoute,
                    tx,
                    budget.value,
                    currency.value,
                    selectedMonth.value,
                    { userDetailViewModel.updateMonth(it) },
                    { transactionViewModel.deleteTx(it) }
                )
                Screen.TransactionsScreen.route -> TransactionsPage(
                    changeRoute,
                    changeNavRoute,
                    tx,
                    selectedMonth.value,
                    { userDetailViewModel.updateMonth(it) },
                    { transactionViewModel.deleteTx(it) }
                )
                Screen.ProfileScreen.route -> SettingsPage(navController, userDetailViewModel)

            }
        }
    }
}
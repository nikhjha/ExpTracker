package com.example.exptracker.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Transaction
import com.example.exptracker.data.dummyTx
import com.example.exptracker.pages.*
import com.example.exptracker.viewmodels.TransactionViewModel
import com.example.exptracker.viewmodels.UserDetailViewModel

@Composable
fun Navigation(
    userDetailViewModel: UserDetailViewModel,
    transactionViewModel: TransactionViewModel
) {
    val navController = rememberNavController()
    val popNavRoute: () -> Unit = {
        navController.popBackStack()
    }
    var route by remember {
        mutableStateOf(Screen.HomeScreen.route)
    }
    val changeRoute: (String) -> Unit = {
        route = it
    }
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartPage(navController, userDetailViewModel)
        }
        composable(route = Screen.SetUpScreen.route) {
            SetupPage(navController, userDetailViewModel)
        }
        composable(route = Screen.MainAppScreen.route) {
            MainApp(navController, route, changeRoute, transactionViewModel, userDetailViewModel)
        }
        composable(route = Screen.AddTxScreen.route) {
            AddTxPage(popNavRoute, transactionViewModel, userDetailViewModel)
        }
        composable(
            route = Screen.EditTxScreen.route + "/{txId}",
            arguments = listOf(navArgument("txId") {
                type = NavType.StringType
            })
        ) {
            EditTxPage(popNavRoute, it.arguments?.getString("txId") ?: "", transactionViewModel)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsPage(navController, userDetailViewModel)
        }
    }
}

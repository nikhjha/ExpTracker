package com.example.exptracker.navigation

sealed class Screen(val route : String){
    object StartScreen : Screen(route = "start")
    object HomeScreen : Screen(route = "home")
    object TransactionsScreen : Screen(route = "transactions")
    object AnalysisScreen : Screen(route = "analysis")
    object ProfileScreen : Screen(route = "profile")
    object AddTxScreen : Screen(route = "add_transaction")
    object EditTxScreen : Screen(route = "edit_transaction")
    object SettingsScreen : Screen(route = "settings")
    fun withArgs(vararg args : String): String{
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }
}

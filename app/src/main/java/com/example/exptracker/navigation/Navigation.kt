package com.example.exptracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exptracker.pages.MainApp
import com.example.exptracker.pages.SetupPage
import com.example.exptracker.pages.StartPage

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartPage(navController)
        }
        composable(route = Screen.SetUpScreen.route) {
            SetupPage()
        }
        composable(route = Screen.MainAppScreen.route){
            MainApp()
        }
    }
}

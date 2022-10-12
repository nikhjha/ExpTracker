package com.example.exptracker.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.exptracker.component.BottomBar
import com.example.exptracker.navigation.Screen


@Composable
fun MainApp(
    navController: NavHostController
) {
    var route by remember {
        mutableStateOf(Screen.HomeScreen.route)
    }
    val changeRoute: (String) -> Unit = {
        route = it
    }
    val changeNavRoute : (String) -> Unit = {
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
        when (route) {
            Screen.HomeScreen.route -> HomePage(changeRoute)
            Screen.TransactionsScreen.route -> TransactionsPage(changeRoute)
            Screen.AnalysisScreen.route -> AnalysisPage()
        }
    }
}
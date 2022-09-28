package com.example.exptracker.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.exptracker.component.BottomBar
import com.example.exptracker.navigation.Screen
import java.lang.reflect.Modifier


@Composable
fun MainApp() {
    var route by remember {
        mutableStateOf(Screen.HomeScreen.route)
    }
    val changeRoute: (String) -> Unit = {
        route = it
    }
    Scaffold(
        bottomBar = { BottomBar(currentRoute = route, changeRoute) },
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
            Screen.HomeScreen.route -> HomePage()
            Screen.TransactionsScreen.route -> TransactionsPage()
        }
    }
}
package com.example.exptracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.ExpTrackerTheme
import kotlinx.coroutines.delay
//import java.util.Timer
//import kotlin.concurrent.schedule
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun StartPage( navController: NavController) {

//    Timer("LoadUp", false).schedule(500){
//        navController.navigate(route = Screen.HomeScreen.route)
//    }
    LaunchedEffect(Unit){
        delay(1.seconds)
        navController.navigate(route = Screen.HomeScreen.route)
    }
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text("ExpTrack", color = Color.White, fontSize = 45.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun StartPagePreview(){
    ExpTrackerTheme {
        StartPage(rememberNavController())
    }
}
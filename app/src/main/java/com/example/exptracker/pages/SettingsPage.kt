package com.example.exptracker.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exptracker.navigation.Screen
import com.example.exptracker.ui.theme.CardColor
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.viewmodels.UserDetailViewModel


@Composable
fun RowButton(title: String, value: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(text = value, color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            IconButton(onClick = onClick) {
                Icon(Icons.Default.ArrowForward, "Select $title", tint = MaterialTheme.colors.primary)
            }
        }
    }
}

@Composable
fun SettingsPage(
    navController: NavController = rememberNavController(),
    userDetailViewModel: UserDetailViewModel = UserDetailViewModel()
) {
    val budget = userDetailViewModel.budget.collectAsState()
    val currency = userDetailViewModel.currency.collectAsState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            IconButton(onClick = {
//                navController.popBackStack()
//            }) {
//                Icon(
//                    Icons.Default.ArrowBack,
//                    contentDescription = "forward arrow for financial report",
//                    tint = Color.DarkGray
//                )
//            }
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = "Settings", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Spacer(modifier = Modifier.width(25.dp))
        }
        RowButton(title = "Budget", value = budget.value.toString()) {
            navController.navigate(Screen.SetUpScreen.route)
        }
        Spacer(modifier = Modifier.height(8.dp))
        RowButton(title = "Currency", value = (currency.value.name)) {
            navController.navigate(Screen.SetUpScreen.route)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPagePreview(){
    ExpTrackerTheme {
        SettingsPage()
    }
}
package com.example.exptracker.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.exptracker.R
import com.example.exptracker.navigation.Screen

data class Navitem(
    @DrawableRes val icon: Int,
    val route: String,
    val label: String,
)

@Composable
fun CustomIcon(it: Navitem, currentRoute: String) {
    if (it.route == Screen.AddTxScreen.route) {
        androidx.compose.material.Icon(
            imageVector = ImageVector.vectorResource(id = it.icon),
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
        return
    }
    androidx.compose.material.Icon(
        imageVector = ImageVector.vectorResource(id = it.icon),
        contentDescription = null,
        tint = if(currentRoute == it.route) MaterialTheme.colors.primary else Color.Gray
    )
}

@Composable
fun BottomBar(currentRoute: String, changeRoute: (String) -> Unit, changeNavRoute : (String) -> Unit) {
    val items: List<Navitem> = listOf(
        Navitem(icon = R.drawable.home, route = Screen.HomeScreen.route, label = "Home"),
        Navitem(
            icon = R.drawable.magicons_glyph_finance_transaction,
            route = Screen.TransactionsScreen.route,
            label = "Transaction"
        ),
        Navitem(icon = R.drawable.add_icon, route = Screen.AddTxScreen.route, label = ""),
        Navitem(
            icon = R.drawable.magicons_glyph_finance_pie_chart,
            route = Screen.AnalysisScreen.route,
            label = "Analysis"
        ),
        Navitem(icon = R.drawable.user, route = Screen.ProfileScreen.route, label = "Profile"),
    )
    Box(Modifier.clickable {}){
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(vertical = 22.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {


            items.forEach {

                val isSelected = it.route == currentRoute

                IconButton(onClick = {
                    if (it.route == Screen.AddTxScreen.route ) {
                        changeNavRoute(it.route)
                    }
                    else if (!isSelected)
                        changeRoute(it.route)
                }) {
                    CustomIcon(it = it, currentRoute = currentRoute)
                }

            }
        }
    }
}
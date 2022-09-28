package com.example.exptracker.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.data.getAllMonths
import com.example.exptracker.R

@Composable
fun SelectMonth(month : CustomMonth, changeMonth: (CustomMonth) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val months : List<CustomMonth> = getAllMonths()
    Box {
        OutlinedButton(
            onClick = { expanded = !expanded },
            border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(0f)),
            contentPadding = PaddingValues(end = 16.dp, start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "DropDown",
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(month.title)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            months.forEach{
                DropdownMenuItem(onClick = {
                    changeMonth(it)
                    expanded = false
                }) {
                    Text(it.title)
                }
            }
        }
    }
}
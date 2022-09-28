package com.example.exptracker.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.exptracker.data.RecentSelection
import com.example.exptracker.ui.theme.CardColor

@Composable
fun CustomButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) CardColor.Orange.backgroundColor else Color.White.copy(
                0f
            )
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = if (selected) CardColor.Orange.primary else Color.Gray
        )
    }
}

@Composable
fun RecentSelector(selectedBase: RecentSelection, changeBase: (base: RecentSelection) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(text = RecentSelection.Today.title, RecentSelection.Today == selectedBase) {
            changeBase(RecentSelection.Today)
        }
        CustomButton(text = RecentSelection.Week.title, RecentSelection.Week == selectedBase) {
            changeBase(RecentSelection.Week)
        }
        CustomButton(text = RecentSelection.Month.title, RecentSelection.Month == selectedBase) {
            changeBase(RecentSelection.Month)
        }
        CustomButton(text = RecentSelection.Year.title, RecentSelection.Year == selectedBase) {
            changeBase(RecentSelection.Year)
        }
    }
}


@Composable
fun FrequencyGraph(){
    var selectedBase by remember { mutableStateOf<RecentSelection>(RecentSelection.Today) }
    val changeBase: (base: RecentSelection) -> Unit = {
        selectedBase = it
    }
    Spacer(modifier = Modifier.height(100.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ){
        RecentSelector(selectedBase, changeBase)
    }
}
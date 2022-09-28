package com.example.exptracker.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFF7F3DFF)
val Purple500 = Color(0xFF7F3DFF)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF0077FF)

sealed class CardColor(val backgroundColor: Color, val primary: Color) {
    object Orange : CardColor(
        Color(255, 213, 128), Color(255, 165, 0)
    )
    object Purple : CardColor(
        Color(207, 159, 255), Purple500
    )
    object Red : CardColor(
        Color(255, 153, 152),
        Color.Red
    )
}

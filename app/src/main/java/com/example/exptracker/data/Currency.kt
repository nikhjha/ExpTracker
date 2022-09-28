package com.example.exptracker.data

data class Currency(
    val id: String,
    val sign: String,
    val name: String,
)

val Currencies = listOf<Currency>(
    Currency("1", "₹", "Rupee"),
    Currency("2", "\$", name = "Dollar"),
    Currency("3", "€", name = "Euro"),
    Currency("4", "£", name = "Pound"),
    Currency("5", "¥", name = "Yen"),
    Currency("6", "₣", name = "Franc"),
    Currency("7", "د.ك", name = "Dinar"),
    Currency("8", "₳", name = "Austral"),
    Currency("9", "₵", name = "Guarani"),
)
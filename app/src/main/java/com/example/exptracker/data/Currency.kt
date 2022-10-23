package com.example.exptracker.data

data class Currency(
    val id: String,
    val sign: String,
    val name: String,
)

val mapOfCurrency = mapOf<String, Currency>(
    "1" to Currency("1", "₹", "Rupee"),
    "2" to Currency("2", "\$", name = "Dollar"),
    "3" to Currency("3", "€", name = "Euro"),
    "4" to Currency("4", "£", name = "Pound"),
    "5" to Currency("5", "¥", name = "Yen"),
    "6" to Currency("6", "₣", name = "Franc"),
    "7" to Currency("7", "د.ك", name = "Dinar"),
    "8" to Currency("8", "₳", name = "Austral"),
    "9" to Currency("9", "₵", name = "Guarani"),
)

val Currencies = mapOfCurrency.values.toList()

fun getCurrency(id : String) : Currency? {
    return mapOfCurrency[id]
}
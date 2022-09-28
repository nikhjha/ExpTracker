package com.example.exptracker.data

data class Currency(
    val id : String,
    val sign : String,
)

val Currencies = listOf<Currency>(
    Currency("1","₹" ),
    Currency("2",  "\$"),
    Currency("3", ""),
    Currency("4", ""),
    Currency("5", ""),
    Currency("6", ""),
    Currency("7", ""),
    Currency("8", ""),
    Currency("9", ""),
)
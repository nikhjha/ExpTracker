package com.example.exptracker.util

import com.example.exptracker.data.Currency

fun CurrencyFormater(num : Float, currency: Currency) : String{
    return "${currency.sign}$num"
}
package com.example.exptracker.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Month

data class Transaction(
    val id : String,
    val amount : Float,
    val description : String,
    val category: Category,
    val dateTime: LocalDateTime
)


val dummyTx : List<Transaction> = listOf(
    Transaction(
        "1",
        120f,
        "Buy Some Grocery",
        Category.Shopping,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44)
    ),
    Transaction(
        "2",
        80f,
        "Disney + Annual Subscriptions",
        Category.Subscription,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44)
    ),
    Transaction(
        "3",
        32f,
        "Buy a ramen",
        Category.Food,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44)
    ),
)

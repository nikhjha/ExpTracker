package com.example.exptracker.data

import java.time.LocalDateTime
import java.time.Month

data class Transaction(
    val id : String,
    val amount : Float,
    val description : String,
    val category: Category,
    val dateTime: LocalDateTime,
    val currency: Currency
)

val nullTx = Transaction(
        "null", 0.0f, "", Category.Miscellaneous, LocalDateTime.now(), Currencies[0]
)

val dummyTx : List<Transaction> = listOf(
    Transaction(
        "1",
        120f,
        "Buy Some Grocery",
        Category.Shopping,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44),
        Currencies[0]
    ),
    Transaction(
        "2",
        80f,
        "Disney + Annual Subscriptions",
        Category.Subscription,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44),
        Currencies[0]
    ),
    Transaction(
        "3",
        32f,
        "Buy a ramen",
        Category.Food,
        LocalDateTime.of(2022, Month.SEPTEMBER, 28, 3, 44),
    Currencies[0]
    ),
    Transaction(
        "4",
        34f,
        "Buy a ramen",
        Category.Food,
        LocalDateTime.of(2022, Month.SEPTEMBER, 27, 3, 44),Currencies[0]
    ),
    Transaction(
        "5",
        2f,
        "Buy a ramen",
        Category.Shopping,
        LocalDateTime.of(2022, Month.SEPTEMBER, 24, 3, 44),Currencies[0]
    ),
    Transaction(
        "6",
        78f,
        "Buy a ramen",
        Category.Subscription,
        LocalDateTime.of(2022, Month.AUGUST, 31, 3, 44),Currencies[0]
    ),
    Transaction(
        "7",
        190f,
        "Buy a ramen",
        Category.Shopping,
        LocalDateTime.of(2022, Month.SEPTEMBER, 1, 3, 44),Currencies[0]
    ),
    Transaction(
        "8",
        34f,
        "Buy a ramen",
        Category.Food,
        LocalDateTime.of(2022, Month.MAY, 28, 3, 44),Currencies[0]
    ),
    Transaction(
        "9",
        64f,
        "Buy a ramen",
        Category.Subscription,
        LocalDateTime.of(2021, Month.SEPTEMBER, 28, 3, 44),Currencies[0]
    ),
)

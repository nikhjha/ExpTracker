package com.example.exptracker.data

import androidx.annotation.DrawableRes
import com.example.exptracker.R
import com.example.exptracker.ui.theme.CardColor

sealed class Category(
    val id: String,
    @DrawableRes val image: Int,
    val title: String,
    val color: CardColor
) {
    object Shopping : Category(
        "shopping",
        R.drawable.user,
        "Shopping",
        CardColor.Orange
    )
    object Subscription : Category(
        "subscription",
        R.drawable.user,
        "Subscription",
        CardColor.Purple
    )
    object Food : Category(
        "food",
        R.drawable.user,
        "Food",
        CardColor.Red
    )
    object Miscellaneous : Category(
        "miscellaneous",
        R.drawable.user,
        "Miscellaneous",
        CardColor.Red
    )
}
fun getAllCategory() : List<Category>{
    return listOf(Category.Shopping, Category.Subscription, Category.Food)
}

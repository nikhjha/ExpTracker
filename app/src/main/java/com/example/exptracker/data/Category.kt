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
        R.drawable.shopping,
        "Shopping",
        CardColor.Orange
    )
    object Subscription : Category(
        "subscription",
        R.drawable.subscription,
        "Subscription",
        CardColor.Purple
    )
    object Food : Category(
        "food",
        R.drawable.food,
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

val mapOfCategory = mapOf<String, Category>(
    Category.Shopping.id to Category.Shopping,
    Category.Subscription.id to Category.Subscription,
    Category.Food.id to Category.Food
)

fun getAllCategory() : List<Category>{
    return mapOfCategory.values.toList()
}

fun getCategory( id : String) : Category? {
    return mapOfCategory[id]
}

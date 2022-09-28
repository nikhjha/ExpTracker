package com.example.exptracker.util

fun encapsulateText( text : String) : String{
    return if(text.length > 15) text.slice(IntRange(0,13)) + ".." else text
}
package com.example.exptracker.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun toTime( dateTime: LocalDateTime) : String{
    return dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
}
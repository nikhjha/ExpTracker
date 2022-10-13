package com.example.exptracker.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

fun toTime( dateTime: LocalDateTime) : String{
    return dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
}

fun toFullTime( dateTime: LocalDateTime) : String{
    return dateTime.atZone(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("d MMM uuuu (HH:mm:ss)"))
}
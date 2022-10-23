package com.example.exptracker.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ProvidedTypeConverter
class DateTimeConverter {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

    @TypeConverter
    fun dateToISO(dateTime: LocalDateTime): String {
        return dateTime.format(formatter)
    }

    @TypeConverter
    fun isoToDate(iso : String): LocalDateTime {
        return LocalDateTime.parse(iso, formatter)
    }
}
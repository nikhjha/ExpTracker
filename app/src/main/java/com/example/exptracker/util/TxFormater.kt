package com.example.exptracker.util

import com.example.exptracker.data.RecentSelection
import com.example.exptracker.data.Transaction
import java.time.LocalDateTime
import java.time.ZoneId

fun isToday(tx: Transaction): Boolean {
    val today = LocalDateTime.now()
    if (today.dayOfMonth == tx.dateTime.dayOfMonth
        && today.month == tx.dateTime.month
        && today.year == tx.dateTime.year
    ) {
        return true
    }
    return false
}

fun calculateDateTimeToMilli(dateTime: LocalDateTime): Long {
    return dateTime.atZone(ZoneId.of("Asia/Kolkata")).toInstant()
        .toEpochMilli()
}

fun isYesterday(tx: Transaction): Boolean {
    val time = calculateDateTimeToMilli(LocalDateTime.now().minusDays(1))
    val txTime = calculateDateTimeToMilli(tx.dateTime)
    if (txTime > time) {
        return true
    }
    return false
}

fun isWeekAgo(tx: Transaction): Boolean {
    val time = calculateDateTimeToMilli(LocalDateTime.now().minusDays(7))
    val txTime = calculateDateTimeToMilli(tx.dateTime)
    if (txTime > time) {
        return true
    }
    return false
}

fun isMonthAgo(tx: Transaction): Boolean {
    val time = calculateDateTimeToMilli(LocalDateTime.now().minusMonths(1))
    val txTime = calculateDateTimeToMilli(tx.dateTime)
    if (txTime > time) {
        return true
    }
    return false
}

fun isYearAgo(tx: Transaction): Boolean {
    val time = calculateDateTimeToMilli(LocalDateTime.now().minusYears(1))
    val txTime = calculateDateTimeToMilli(tx.dateTime)
    if (txTime > time) {
        return true
    }
    return false
}

fun groupByRecentSelection(list: List<Transaction>): Map<RecentSelection, List<Transaction>> {
    val group: MutableMap<RecentSelection, List<Transaction>> = mutableMapOf(
        RecentSelection.Today to listOf<Transaction>(),
        RecentSelection.Yesterday to listOf<Transaction>(),
        RecentSelection.Week to listOf<Transaction>(),
        RecentSelection.Month to listOf<Transaction>(),
        RecentSelection.Year to listOf<Transaction>(),
        RecentSelection.Oldest to listOf<Transaction>()
    )
    list.forEach {
        if (isToday(it)) {
            group[RecentSelection.Today] =
                group[RecentSelection.Today]?.plus(listOf(it)) ?: listOf(it)
        } else if (isYesterday(it)) {
            group[RecentSelection.Yesterday] =
                group[RecentSelection.Yesterday]?.plus(listOf(it)) ?: listOf(it)
        } else if (isWeekAgo(it)) {
            group[RecentSelection.Week] =
                group[RecentSelection.Week]?.plus(listOf(it)) ?: listOf(it)
        } else if (isMonthAgo(it)) {
            group[RecentSelection.Month] =
                group[RecentSelection.Month]?.plus(listOf(it)) ?: listOf(it)
        } else if (isYearAgo(it)) {
            group[RecentSelection.Year] =
                group[RecentSelection.Year]?.plus(listOf(it)) ?: listOf(it)
        } else {
            group[RecentSelection.Oldest] =
                group[RecentSelection.Oldest]?.plus(listOf(it)) ?: listOf(it)
        }
    }
    return group.toMap()
}

fun groupByCumulativeSelection(list: List<Transaction>): Map<RecentSelection, List<Transaction>> {
    val group: MutableMap<RecentSelection, List<Transaction>> = mutableMapOf(
        RecentSelection.Today to listOf<Transaction>(),
        RecentSelection.Yesterday to listOf<Transaction>(),
        RecentSelection.Week to listOf<Transaction>(),
        RecentSelection.Month to listOf<Transaction>(),
        RecentSelection.Year to listOf<Transaction>(),
        RecentSelection.Oldest to listOf<Transaction>()
    )
    list.forEach {
        if (isToday(it)) {
            group[RecentSelection.Today] =
                group[RecentSelection.Today]?.plus(listOf(it)) ?: listOf(it)
        }
        if (isYesterday(it)) {
            group[RecentSelection.Yesterday] =
                group[RecentSelection.Yesterday]?.plus(listOf(it)) ?: listOf(it)
        }
        if (isWeekAgo(it)) {
            group[RecentSelection.Week] =
                group[RecentSelection.Week]?.plus(listOf(it)) ?: listOf(it)
        }
        if (isMonthAgo(it)) {
            group[RecentSelection.Month] =
                group[RecentSelection.Month]?.plus(listOf(it)) ?: listOf(it)
        }
        if (isYearAgo(it)) {
            group[RecentSelection.Year] =
                group[RecentSelection.Year]?.plus(listOf(it)) ?: listOf(it)
        }
        group[RecentSelection.Oldest] =
            group[RecentSelection.Oldest]?.plus(listOf(it)) ?: listOf(it)
    }
    return group.toMap()
}
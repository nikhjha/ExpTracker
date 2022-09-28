package com.example.exptracker.data

sealed class RecentSelection(val title : String, val id : String){
    object Today : RecentSelection("Today", "today")
    object Yesterday : RecentSelection("Yesterday", "yesterday")
    object Week : RecentSelection("Week", "week")
    object Month : RecentSelection("Month", "month")
    object Year : RecentSelection("Year", "year")
    object Oldest : RecentSelection(title = "Old", id = "old")
}

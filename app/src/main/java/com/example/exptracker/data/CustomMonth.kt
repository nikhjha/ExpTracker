package com.example.exptracker.data

import java.time.Month

sealed class CustomMonth(val month: Month, val title : String){
    object  Jan : CustomMonth(Month.JANUARY, "January")
    object  Feb : CustomMonth(Month.FEBRUARY, "February")
    object  Mar : CustomMonth(Month.MARCH, "March")
    object  Apr : CustomMonth(Month.APRIL, "April")
    object  May : CustomMonth(Month.MAY, "May")
    object  Jun : CustomMonth(Month.JUNE, "June")
    object  Jul : CustomMonth(Month.JULY, "July")
    object  Aug : CustomMonth(Month.AUGUST, "August")
    object  Sep : CustomMonth(Month.SEPTEMBER, "September")
    object  Oct : CustomMonth(Month.OCTOBER, "October")
    object  Nov : CustomMonth(Month.NOVEMBER, "November")
    object  Dec : CustomMonth(Month.DECEMBER, "December")

}
fun getAllMonths() : List<CustomMonth>{
    return listOf(
        CustomMonth.Jan,
        CustomMonth.Feb,
        CustomMonth.Mar,
        CustomMonth.Apr,
        CustomMonth.May,
        CustomMonth.Jun,
        CustomMonth.Jul,
        CustomMonth.Aug,
        CustomMonth.Sep,
        CustomMonth.Oct,
        CustomMonth.Nov,
        CustomMonth.Dec
    )
}
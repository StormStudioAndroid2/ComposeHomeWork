package com.example.testcomposaapplication

import java.util.*

data class DayCell(
    val dayOfMonth: Int,
    val dayOfWeek: Int
) {
    fun isWeekend() = dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY
}
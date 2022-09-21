package com.example.testcomposaapplication

sealed class Screen {
    class CalendarScreen(val calendarState: CalendarState): Screen()
    class ScheduleScreen(val day: Int): Screen()
}
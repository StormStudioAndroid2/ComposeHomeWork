package com.example.testcomposaapplication

import android.graphics.Color.pack
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.testcomposaapplication.ui.theme.DesignSystemTheme
import com.example.testcomposaapplication.ui.theme.fonts.MTSBlack
import java.time.DayOfWeek
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignSystemTheme {
                Surface(color = DesignSystemTheme.colors.backgroundPrimary) {
                    RenderApplication()
                }
            }
        }
    }

    @Composable
    fun RenderApplication() {
        val calendarState = remember { mutableStateOf(initState()) }
        val screenState = remember { mutableStateOf(initScreenState(calendarState.value)) }
        Column(
            Modifier
                .fillMaxSize()
                .background(DesignSystemTheme.colors.backgroundPrimary)
        ) {
            when (val state = screenState.value) {
                is Screen.CalendarScreen -> {
                    TableScreen(calendar = state.calendarState) { day ->
                        screenState.value = Screen.ScheduleScreen(day)
                    }
                }
                is Screen.ScheduleScreen -> {
                    ScheduleScreen(
                        state.day,
                        listOf(
                            ScheduleElement(
                                "1:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "2:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "3:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "4:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "5:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "6:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "7:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "8:00",
                                "Спать"
                            ),
                            ScheduleElement(
                                "9:00",
                                "Подъем"
                            ),
                            ScheduleElement(
                                "10:00",
                                "Работа Работа Работа Работа Работа Работа Работа Работа "
                            ),
                        )
                    )
                }
            }
        }
    }
}

const val WEEK_DAY = 7

fun initState(): CalendarState {
    val calendar = Calendar.getInstance()
    val monthNum = calendar.get(Calendar.MONTH)
    val daysCount = calendar.getActualMaximum(Calendar.DATE)
    return CalendarState(
        year = calendar.get(Calendar.YEAR),
        month = monthNum,
        startDate = null,
        endDate = null,
        daysCount = daysCount
    )
}

fun initScreenState(calendarState: CalendarState): Screen {
    return Screen.CalendarScreen(calendarState)
}


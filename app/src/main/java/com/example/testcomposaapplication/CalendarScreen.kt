package com.example.testcomposaapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcomposaapplication.ui.theme.DesignSystemTheme
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TableScreen(calendar: CalendarState, buttonCallback: (day: Int) -> Unit) {
    val tableData = (1..calendar.daysCount).toList().map {
        DayCell(it, Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, it)
        }.get(Calendar.DAY_OF_WEEK))
    }
    val titleData = listOf(
        "Пн",
        "Вт",
        "Ср",
        "Чт",
        "Пт",
        "Сб",
        "Вс"
    )
    val selectedValue = remember { mutableStateOf(-1) }
    Row(Modifier.padding(20.dp, 20.dp, 0.dp, 12.dp)) {
        Text(
            text = "Сентябрь 2022",
            fontSize = 20.sp,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_shape),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(WEEK_DAY),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    ) {
        items(titleData) { HeaderCell(title = it) }
        items(count = WEEK_DAY - tableData.first().dayOfWeek + 1) { }
        items(tableData) {
            TableCell(
                day = it.dayOfMonth.toString(),
                isWeekend = it.isWeekend(),
                isSelected = selectedValue.value == it.dayOfMonth
            ) { selectedValue.value = it.dayOfMonth }
        }
    }
    Button(
        onClick = {
            buttonCallback.invoke(selectedValue.value)
        },
        modifier = Modifier
            .clip(
                RoundedCornerShape(7.dp)
            )
            .fillMaxWidth()
            .padding(start = 42.dp, top = 26.dp, end = 36.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primary_color),
            disabledBackgroundColor = colorResource(R.color.grey)
        ),
        enabled = selectedValue.value != -1
    ) {
        Text(
            text = "Открыть расписание",
            style = DesignSystemTheme.typography.p2.medium,
            color = Color.White
        )

    }
}

@Composable
fun TableCell(
    day: String,
    isWeekend: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = day,
        modifier = Modifier
            .padding(8.dp)
            .background(
                if (isSelected) colorResource(id = R.color.selected_color) else Color.White
            )
            .clickable { onClick.invoke() }
            .clip(shape = RoundedCornerShape(12.dp)),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = if (isWeekend) colorResource(R.color.grey) else Color.Black,
        style = DesignSystemTheme.typography.h3.regular
    )
}

@Composable
fun HeaderCell(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = colorResource(R.color.grey),
        style = DesignSystemTheme.typography.p3.mediumUppercase
    )
}
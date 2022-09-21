package com.example.testcomposaapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testcomposaapplication.ui.theme.DesignSystemTheme

@Composable
fun ScheduleScreen(day: Int, messages: List<ScheduleElement>) {
    Text(
        text = "Планы на $day число",
        style = DesignSystemTheme.typography.h3.medium,
        modifier = Modifier.padding(top = 24.dp, start = 20.dp, bottom = 13.dp)
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages) {
            ScheduleRow(scheduleElement = it)
        }
    }
}

@Composable
fun ScheduleRow(scheduleElement: ScheduleElement) {
    Row(
        Modifier.padding(start = 28.dp, end = 24.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bullet_l),
            modifier = Modifier
                .padding(end = 12.dp)
                .fillMaxHeight(),
            contentDescription = "",
        )
        Text(
            text = "${scheduleElement.time}. ${scheduleElement.business}",
            style = DesignSystemTheme.typography.h3.regular,
            modifier = Modifier
                .fillMaxWidth()
        )
        //            )
//        Image(
//            painter = painterResource(id = R.drawable.ic_bullet_l),
//            contentDescription = "",
//            alignment = Alignment.Center
//        )
//        Text(
//            text = "${scheduleElement.time}. ${scheduleElement.business}",
//            style = DesignSystemTheme.typography.h3.regular,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}
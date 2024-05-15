package com.example.mislicloneapp.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun formatTime(milliseconds: Long): String {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    formatter.timeZone = TimeZone.getDefault() // Sistem varsayılan zaman dilimini kullan
    return formatter.format(milliseconds)
}

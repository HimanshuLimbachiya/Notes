package com.example.notes.utils

import java.text.SimpleDateFormat
import java.util.*


fun getCurrentDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(Date())
}
fun getCurrentTime(): String {
    return SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(Date())
}
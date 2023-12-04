package com.example.homework1.util

import kotlin.math.roundToInt

fun getTimeStringFromDouble(time: Double): String {
    val resultInt = time.roundToInt()
    val hours = resultInt % 86400 / 3600
    val minutes = resultInt % 86400 % 3600 / 60
    val seconds = resultInt % 86400 % 3600 % 60

    return makeTimeString(hours, minutes, seconds)
}

fun makeTimeString(hour: Int, min: Int, sec: Int): String =
    String.format("%02d:%02d:%02d", hour, min, sec)
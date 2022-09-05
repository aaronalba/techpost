package com.aaron.techpost.util

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.util.*

private const val FORMAT = "EEEE, MMM d"
private const val HOUR = "hour"
private const val MINUTE = "minute"

/**
 * Helper function for formatting dates using <day name>, <day> <month> format.
 */
fun formatDate(format: String = FORMAT, date: Date): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}

/**
 * Functions that computes for the difference between the current time and the given utc time.
 * @return Duration
 */
fun elapsedTime(utc: String): Duration {
    val instant = Instant.parse(utc)
    val now = Instant.now()
    return Duration.between(instant, now)
}

/**
 * Function that computes for the elapsed time between the given utc time string and the current time.
 */
fun formatElapsedTime(utc: String): String {
    val dif = elapsedTime(utc)
    val minutes: Long = dif.toMinutes()
    val hours: Long = dif.toHours()

    return if (hours > 0) {
        if (hours == 1L) {
            "1 $HOUR ago"
        } else {
            "$hours ${HOUR}s ago"
        }
    } else if (minutes > 0) {
        if (minutes == 1L) {
            "1 $MINUTE ago"
        } else {
            "$minutes ${MINUTE}s ago"
        }
    } else {
        "Just now"
    }
}
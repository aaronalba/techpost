package com.aaron.techpost.util

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT = "EEEE, dd MMM"

/**
 * Helper function for formatting dates using <day name>, <day> <month> format.
 */
fun formatDate(format: String = FORMAT, date: Date): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}
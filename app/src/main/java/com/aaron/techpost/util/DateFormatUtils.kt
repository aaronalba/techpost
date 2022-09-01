package com.aaron.techpost.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper function for formatting dates.
 */
fun formatDate(format: String, date: Date): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}
package com.aaron.techpost.util

/**
 * Function that removes the source name from the title string. The default format of the article
 * title from api is appended with the source name at the end. The UI only needs the title since the
 * source name is shown in a separate text view.
 */
fun processTitle(title: String): String {
    val list: List<String> = title.split("-")
    return if (list.size == 1) {
        list.joinToString("")
    } else {
        list.dropLast(1).joinToString("")
    }
}
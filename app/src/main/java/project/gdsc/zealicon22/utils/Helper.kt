package project.gdsc.zealicon22.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateTime(datetime: String): Date =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        .apply { timeZone = TimeZone.getTimeZone("UTC") }
        .parse(datetime.substringBefore('+'))!!

fun Date.formatTo(
    dateFormat: String,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

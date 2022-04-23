package project.gdsc.zealicon22.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun getDateTime(datetime: String): Date =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
//        .apply { timeZone = TimeZone.getTimeZone("IST") }
        .parse(datetime.substringBefore('+'))!!

fun Date.formatTo(
    dateFormat: String,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

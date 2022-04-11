package project.gdsc.zealicon22.models

import android.annotation.SuppressLint
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Nakul
 * on 07,April,2022
 */
@Entity(tableName = "events_table")
data class Events(
    val id: Int,
    val name: String,
    val description: String,
    val datetime: String,
    val duration: String,
    val society: String,
    val category: String,
    val prizes: String,
    @Embedded
    val contact: Contact?,
    val is_active: Boolean,
    val rules: String,
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0
) {
    var day: Int

    init {
        day = (getDateTime().formatTo("dd").toIntOrNull()?:26) - 25
    }

    fun getDateTime(): Date =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            .apply{ timeZone = TimeZone.getTimeZone("UTC") }
            .parse(datetime.substringBefore('+'))!!

    private fun Date.formatTo(
        dateFormat: String,
        timeZone: TimeZone = TimeZone.getDefault()
    ): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
}

data class Contact(
    val fullname : String,
    val contact_no : String
)

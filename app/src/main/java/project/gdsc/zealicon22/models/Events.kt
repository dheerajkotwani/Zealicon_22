package project.gdsc.zealicon22.models

import android.annotation.SuppressLint
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import project.gdsc.zealicon22.utils.formatTo
import project.gdsc.zealicon22.utils.getDateTime
import timber.log.Timber
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
    val venue: String,
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
        day = (getDateTime(datetime).formatTo("dd").toIntOrNull()?:26) - 25
    }

}


data class Contact(
    val first_name : String,
    val last_name : String,
    @Embedded
    val userdetails : UserDetails?
)

data class UserDetails (
    val contact_no: String
)
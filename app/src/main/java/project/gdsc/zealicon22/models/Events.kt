package project.gdsc.zealicon22.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Nakul
 * on 07,April,2022
 */
@Entity(tableName = "events_table")
data class Events(
    val id: Int,
    val name: String,
    val description: String,
    val society_id: Int,
    val category_id: Int,
    val winner1: Int,
    val winner2: Int,
    val contact_name: String,
    val contact_no: String,
    val is_active: Int,
    val rules: String,
    val day: Int,
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0
)
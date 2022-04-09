package project.gdsc.zealicon22.dayWiseEvent

import project.gdsc.zealicon22.models.Events

data class EventsModel(
    val id: Int,
    val title: String,
    val day: Int,
    val category: String
) {
    companion object {
        fun fromEvent(e: Events) = EventsModel(
            e.id, e.name, e.day, e.description
        )
    }
}
package project.gdsc.zealicon22.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.gdsc.zealicon22.models.Events

/**
 * @author Dheeraj Kotwani on 03/04/22.
 */
@Database(entities = [Events::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun getEventsDao(): EventsDao

    companion object {

        @Volatile
        private var instance: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: Room.databaseBuilder(context, RoomDB::class.java, "Zealicon.db")
                .fallbackToDestructiveMigration().build()
        }

    }


}
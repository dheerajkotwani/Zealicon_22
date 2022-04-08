package project.gdsc.zealicon22.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import project.gdsc.zealicon22.models.Events

/**
 * Created by Nakul
 * on 07,April,2022
 */
@Dao
interface EventsDao {

    @Query("SELECT * FROM events_table")
    fun getAllEvents(): Flow<List<Events>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvents(list: List<Events>)
}
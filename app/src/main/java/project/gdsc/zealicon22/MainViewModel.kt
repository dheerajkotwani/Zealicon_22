package project.gdsc.zealicon22

import android.content.SharedPreferences
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.RegisterBody
import project.gdsc.zealicon22.models.ResultHandler
import java.util.*
import javax.inject.Inject

/**
 * Created by Nakul
 * on 07,April,2022
 * @Updated: Karan Verma on 09/04/22
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    var subscribed = false

    private val mEvents = MutableLiveData<ResultHandler<List<Events>>>()
    val events: LiveData<ResultHandler<List<Events>>> = mEvents

    /*
    * Use transformations on events to get your selective data. Example for
    * getting day 1 events is shown below:
    * val dayOneEvents: LiveData<ResultHandler<List<Events>>> = Transformations.map(events) {
    *   if (it is ResultHandler.Success)
    *       ResultHandler.Success(it.result.filter { i-> i.day == 1 })
    *   else it
    * }
    * */

    val upcomingEvents: LiveData<ResultHandler<List<Events>>> = Transformations.map(events) {
        if (it is ResultHandler.Success)
            ResultHandler.Success(it.result.filter { i -> i.getDateTime() > Date() })
        else it
    }

    private val mQuery = MutableLiveData<String?>(null)
    private val mCategory = MutableLiveData<String?>(null)

    val searchedEvents: LiveData<List<Events>> = mQuery.combineWith(events) { q, it ->
        if (it is ResultHandler.Success && !q.isNullOrBlank())
            it.result.filter { i ->
                i.name.contains(q, true)
            }
        else listOf()
    }.combineWith(mCategory) { e, c ->
        if (c.isNullOrBlank())
            e.orEmpty()
        else e?.filter { i -> i.category == c }.orEmpty()
    }

    private val mDay = MutableLiveData<Int?>(null)
    val selectedDay: LiveData<List<Events>> = mDay.combineWith(events) { day, events ->
        if (events is ResultHandler.Success)
            events.result.filter { it.day == day }
        else listOf()
    }

    private fun <T, K, R> LiveData<T>.combineWith(
        liveData: LiveData<K>,
        block: (T?, K?) -> R
    ): LiveData<R> {
        val result = MediatorLiveData<R>()
        result.addSource(this) {
            result.value = block(this.value, liveData.value)
        }
        result.addSource(liveData) {
            result.value = block(this.value, liveData.value)
        }
        return result
    }

    init {
        getEvents()
    }

    fun getEvents() = viewModelScope.launch {
        mEvents.postValue(ResultHandler.Loading)
        repo.getEvents().collect {
            mEvents.postValue(it)
        }
    }

    fun searchEvents(query: CharSequence?) {
        if (query.isNullOrBlank())
            mQuery.value = null
        else
            mQuery.value = query.toString()
    }

    fun selectDay(day: Int) {
        mDay.value = day
    }

    fun selectCategory(category: String?) {
        if (category.isNullOrBlank())
            mCategory.value = null
        else
            mCategory.value = category
    }

}
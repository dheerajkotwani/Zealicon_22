package project.gdsc.zealicon22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.ResultHandler
import javax.inject.Inject

/**
 * Created by Nakul
 * on 07,April,2022
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) :
    ViewModel() {

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

    init {
        getEvents()
    }

    private fun getEvents() = viewModelScope.launch {
        mEvents.postValue(ResultHandler.Loading)
        repo.getEvents().collect {
            mEvents.postValue(it)
        }
    }
}
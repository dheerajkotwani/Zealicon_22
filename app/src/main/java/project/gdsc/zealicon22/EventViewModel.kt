package project.gdsc.zealicon22

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import project.gdsc.zealicon22.models.RegisterBody
import project.gdsc.zealicon22.models.ResultHandler
import javax.inject.Inject

/**
 * Created by Nakul
 * on 12,April,2022
 */
@HiltViewModel
class EventViewModel @Inject constructor(
    private val repo: Repository,
    private val sp: SharedPreferences
) : ViewModel() {

    private val mRegistration= MutableLiveData<ResultHandler<Any>>()
    val registration: LiveData<ResultHandler<Any>> = mRegistration

    fun registerForEvent(id: String) = viewModelScope.launch {
        mRegistration.postValue(ResultHandler.Loading)
        val zealId = sp.getString("ZEAL_ID", null)
        if (zealId.isNullOrBlank())
            mRegistration.postValue(ResultHandler.Failure(Throwable("Zeal Id not found!!")))
        else
            repo.registerForEvent(RegisterBody(zealId, id)).collect {
                mRegistration.postValue(it)
            }
    }

}
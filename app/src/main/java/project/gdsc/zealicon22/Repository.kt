package project.gdsc.zealicon22

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import project.gdsc.zealicon22.database.EventsDao
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.ResultHandler
import project.gdsc.zealicon22.network.NetworkService
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Nakul
 * on 08,April,2022
 */
class Repository @Inject constructor(
    private val sp: SharedPreferences,
    private val dao: EventsDao,
    private val api: NetworkService
) {

    companion object {
        const val DATA_STORED = "DATA_STORED"
    }

    suspend fun getEvents() = flow {
        emit(ResultHandler.Loading)

        if (sp.getBoolean(DATA_STORED, false))
            dao.getAllEvents().collect { res ->
                emit(ResultHandler.Success(res))
            }
        else fetchDataFromNetwork().collect {
            if (it is ResultHandler.Success) {
                emit(it)
                saveEvents(it.result)
                sp.edit().putBoolean(DATA_STORED, true).apply()
            } else emit(it)
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun fetchDataFromNetwork() = flow {
        runCatching {
            emit(ResultHandler.Success(api.getEvents().body()!!))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn(Dispatchers.IO)

    suspend fun getOrderId() = flow {
        runCatching {
            emit(ResultHandler.Success(api.getOrderId().body()!!))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn(Dispatchers.IO)

    suspend fun submitReceipt(paymentReceipt: PaymentReceipt) = flow {
        runCatching {
            Timber.d("PR $paymentReceipt")
            emit(ResultHandler.Success(api.submitReceipt(
                paymentReceipt
            ).body()!!))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn((Dispatchers.IO))

    private suspend fun saveEvents(list: List<Events>) {
        dao.saveEvents(list)
    }
}
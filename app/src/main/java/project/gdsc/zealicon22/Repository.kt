package project.gdsc.zealicon22

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import project.gdsc.zealicon22.database.EventsDao
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.RegisterBody
import project.gdsc.zealicon22.models.ResultHandler
import project.gdsc.zealicon22.network.NetworkService
import project.gdsc.zealicon22.utils.getDateTime
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
        const val MY_DATA_STORED = "MY_DATA_STORED"
    }

    private var fetched = false

    suspend fun getEvents() = flow {

        emit(ResultHandler.Success(dao.getAllEvents()))

        if (!fetched)
            fetchDataFromNetwork().collect {
                if (it is ResultHandler.Success) {
                    emit(it)
                    eraseEvents()
                    saveEvents(it.result)
                    sp.edit().putBoolean(DATA_STORED, true).apply()
                }
            }

    }.flowOn(Dispatchers.IO)

    suspend fun getMyEvents(zealId: String) = flow {
        emit(ResultHandler.Loading)
        fetchMyEventsFromNetwork(zealId).collect {
            if (it is ResultHandler.Success) {
                it.result.forEach { e ->
                    sp.edit().putBoolean("EventId:${e.event.id}", true).apply()
                }
                emit(it)
            } else emit(it)
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun fetchDataFromNetwork() = flow {
        runCatching {
            emit(ResultHandler.Success(api.getEvents().body()!!.sortedBy { getDateTime(it.datetime) }))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn(Dispatchers.IO)

    private suspend fun fetchMyEventsFromNetwork(zealId: String) = flow {
        runCatching {
            Timber.d("Zeal $zealId")
            Timber.d("Zeal ${api.getMyEvents(zealId).body()!!}")
            emit(ResultHandler.Success(api.getMyEvents(zealId).body()!!))
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
            emit(
                ResultHandler.Success(
                    api.submitReceipt(
                        paymentReceipt
                    ).body()!!
                )
            )
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn((Dispatchers.IO))

    suspend fun findZealId(q: String) = flow {
        runCatching {
            emit(ResultHandler.Success(api.findZealId(q).body()!!))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn((Dispatchers.IO))

    private suspend fun saveEvents(list: List<Events>) {
        dao.saveEvents(list)
    }

    private suspend fun eraseEvents() {
        dao.deleteEvents()
    }

    suspend fun registerForEvent(body: RegisterBody) = flow {
        runCatching {
            emit(ResultHandler.Success(api.registerForEvent(body).body()!!))
        }.getOrElse { emit(ResultHandler.Failure(it)) }
    }.flowOn(Dispatchers.IO)

    suspend fun validateUser(
        admission_no: String,
        email: String,
        fullname: String,
        contact_no: String,
        college: String
    ) = flow {
        runCatching {
            api.validateUser(admission_no, email, fullname, contact_no, college)
            emit(
                ResultHandler.Success(
                    api.validateUser(
                        admission_no,
                        email,
                        fullname,
                        contact_no,
                        college
                    ).body()!!
                )
            )
        }.getOrElse {
            emit(ResultHandler.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

}
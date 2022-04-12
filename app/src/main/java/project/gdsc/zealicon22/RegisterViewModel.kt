package project.gdsc.zealicon22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.PaymentResponse
import project.gdsc.zealicon22.models.PaymentSuccess
import project.gdsc.zealicon22.models.ResultHandler
import javax.inject.Inject
import project.gdsc.zealicon22.models.ValidateResponse

/**
 * Created by Anuraj Jain
 * on 10,April,2022
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: Repository) :
    ViewModel(){

    private val mOrderId = MutableLiveData<ResultHandler<PaymentResponse>>()
    val orderId: LiveData<ResultHandler<PaymentResponse>> = mOrderId

    private val mSubmitReceipt  = MutableLiveData<ResultHandler<PaymentSuccess>>()
    val submitReceipt: LiveData<ResultHandler<PaymentSuccess>> = mSubmitReceipt

    private val mValidUser  = MutableLiveData<ResultHandler<ValidateResponse>>()
    val validUser: LiveData<ResultHandler<ValidateResponse>> = mValidUser

    fun getOrderId() = viewModelScope.launch {
        mOrderId.postValue(ResultHandler.Loading)
        repo.getOrderId().collect {
            mOrderId.postValue(it)
        }
    }

    fun submitReceipt(paymentReceipt: PaymentReceipt) = viewModelScope.launch {
        mSubmitReceipt.postValue(ResultHandler.Loading)
        repo.submitReceipt(paymentReceipt).collect {
            mSubmitReceipt.postValue(it)
        }
    }

    fun findZealId(q: String) = viewModelScope.launch {
        mSubmitReceipt.postValue(ResultHandler.Loading)
        repo.findZealId(q).collect {
            mSubmitReceipt.postValue(it)
        }
    }

    fun validateUserData(
        admission_no: String,
        email: String,
        fullname: String,
        contact_no: String,
        college: String
    ) = viewModelScope.launch {
        mValidUser.postValue(ResultHandler.Loading)
        repo.validateUser(
            admission_no, email, fullname, contact_no, college
        ).collect {
            mValidUser.postValue(it)
        }
    }
}
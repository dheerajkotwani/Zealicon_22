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

    fun getOrderId(phone: String) = viewModelScope.launch {
        mOrderId.postValue(ResultHandler.Loading)
        repo.getOrderId(phone).collect {
            mOrderId.postValue(it)
        }
    }

    fun submitReceipt(paymentReceipt: PaymentReceipt) = viewModelScope.launch {
        mSubmitReceipt.postValue(ResultHandler.Loading)
        repo.submitReceipt(paymentReceipt).collect {
            mSubmitReceipt.postValue(it)
        }
    }
}
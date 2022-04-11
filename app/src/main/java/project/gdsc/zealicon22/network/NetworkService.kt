package project.gdsc.zealicon22.network

import okhttp3.RequestBody
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.PaymentResponse
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.PaymentSuccess
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Nakul
 * on 08,April,2022
 */
interface NetworkService {

    @GET("/events/all")
    suspend fun getEvents(): Response<List<Events>>

    @GET("/payment")
    suspend fun getOrderId(): Response<PaymentResponse>

    @POST("/payment/")
    suspend fun submitReceipt(
        @Body paymentReceipt: PaymentReceipt
    ): Response<PaymentSuccess>
}
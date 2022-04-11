package project.gdsc.zealicon22.network

import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.PaymentResponse
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.PaymentSuccess
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Nakul
 * on 08,April,2022
 */
interface NetworkService {

    @GET("/events/all")
    suspend fun getEvents(): Response<List<Events>>

    @GET("/payment")
    suspend fun getOrderId(@Field("user_identity") phone: String): Response<PaymentResponse>

    @POST("/payment")
    suspend fun submitReceipt(@Body body: PaymentReceipt): Response<PaymentSuccess>
}
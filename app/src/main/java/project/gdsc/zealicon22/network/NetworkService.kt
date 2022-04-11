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

    @Multipart
    @POST("/payment/")
    suspend fun submitReceipt(
        @Part("razorpay_payment_id") razorpay_payment_id: String,
        @Part("razorpay_order_id") razorpay_order_id: String,
        @Part("razorpay_signature") razorpay_signature: String,
        @Part("server_order_id") server_order_id: String,
        @Part("admission_no") admission_no: String,
        @Part("college") college: String,
        @Part("contact_no") contact_no: String,
        @Part("fullname") fullname: String,
        @Part("email") email: String
    ): Response<PaymentSuccess>
}
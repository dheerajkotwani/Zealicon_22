package project.gdsc.zealicon22.network

import okhttp3.RequestBody
import project.gdsc.zealicon22.models.*
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

    @POST("/events/registration/")
    suspend fun registerForEvent(@Body registerBody: RegisterBody): Response<Any>

    @GET("/accounts/users/")
    suspend fun findZealId(@Query("query") q: String): Response<PaymentSuccess>

    @GET("/accounts/validate_user_details")
    suspend fun validateUser(
        @Query("admission_no") admission_no: String,
        @Query("email") email: String,
        @Query("fullname") fullname: String,
        @Query("contact_no") contact_no: String,
        @Query("college") college: String,
    ): Response<ValidateResponse>
}
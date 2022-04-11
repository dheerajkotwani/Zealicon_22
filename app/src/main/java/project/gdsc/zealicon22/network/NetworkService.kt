package project.gdsc.zealicon22.network

import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.ModelWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Nakul
 * on 08,April,2022
 */
interface NetworkService {

    @GET("/events/all")
    suspend fun getEvents(): Response<List<Events>>

}
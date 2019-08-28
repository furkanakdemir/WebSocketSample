package net.furkanakdemir.websocketsample.network

import net.furkanakdemir.websocketsample.data.RemoteResponse
import retrofit2.http.GET

interface MessageService {

    @GET("/emredirican/mock-api/db")
    suspend fun getMessages(): RemoteResponse
}

package net.furkanakdemir.websocketsample.data

interface MessageRepository {

    suspend fun getMessages(): Result
}

package net.furkanakdemir.websocketsample.network.websocket

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel

interface EventRepository {

    @ExperimentalCoroutinesApi
    suspend fun getMessages(): Channel<WebSocketEventRepository.WebSocketEvent>

    fun register()

    fun unregister()

    fun send(message: String)
}

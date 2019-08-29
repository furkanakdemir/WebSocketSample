package net.furkanakdemir.websocketsample.network.websocket

import kotlinx.coroutines.channels.Channel

interface EventRepository {

    suspend fun getMessages(): Channel<WebSocketEventRepository.WebSocketEvent>

    fun register()

    fun unregister()

    fun send(message: String)
}
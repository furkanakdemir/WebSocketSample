package net.furkanakdemir.websocketsample.network.websocket

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ExperimentalCoroutinesApi
class WebSocketEventRepository @Inject constructor(private val socketService: WebSocketService) :
    EventRepository {
    private val channel = Channel<WebSocketEvent>(1)
    override suspend fun getMessages(): Channel<WebSocketEvent> {
        return channel
    }

    override fun register() {
        socketService.start(object : WebSocketService.CustomWebSocketListener {
            override fun onConnected() {
                runBlocking {
                    channel.send(WebSocketEvent.Connected)
                }
            }

            override fun onDisconnected() {
                runBlocking {
                    channel.send(WebSocketEvent.Disconnected)
                }
            }

            override fun onMessageReceived(message: String) {
                push(message)
            }
        })
    }

    private fun push(message: String) {
        runBlocking {
            channel.send(WebSocketEvent.Message(message))
        }
    }

    override fun unregister() {
        socketService.close()
    }

    override fun send(message: String) {
        socketService.send(message)
    }

    sealed class WebSocketEvent {

        object Connected : WebSocketEvent()
        object Disconnected : WebSocketEvent()
        class Message(val message: String) : WebSocketEvent()
    }
}

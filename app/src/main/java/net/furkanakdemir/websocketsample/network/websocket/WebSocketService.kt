package net.furkanakdemir.websocketsample.network.websocket

import okhttp3.*
import javax.inject.Inject

class WebSocketService @Inject constructor() : WebSocketListener() {

    private lateinit var listener: CustomWebSocketListener
    private lateinit var ws: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient

    fun start(customWebSocketListener: CustomWebSocketListener) {

        listener = customWebSocketListener
        client = OkHttpClient()
        request = Request.Builder().url("wss://echo.websocket.org").build()

        ws = client.newWebSocket(request, this)

        client.dispatcher.executorService.shutdown()
    }

    fun close() {
        ws.close(NORMAL_CLOSURE_STATUS, null)
    }

    fun send(message: String) {
        ws.send(message)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        listener.onDisconnected()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        listener.onMessageReceived(text)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        listener.onConnected()
    }

    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    interface CustomWebSocketListener {

        fun onConnected()
        fun onDisconnected()
        fun onMessageReceived(message: String)
    }
}

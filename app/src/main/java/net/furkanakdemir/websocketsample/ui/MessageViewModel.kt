package net.furkanakdemir.websocketsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.furkanakdemir.websocketsample.data.Message
import net.furkanakdemir.websocketsample.data.MessageRepository
import net.furkanakdemir.websocketsample.data.Result
import net.furkanakdemir.websocketsample.network.websocket.EventRepository
import net.furkanakdemir.websocketsample.network.websocket.WebSocketEventRepository
import net.furkanakdemir.websocketsample.util.SingleLiveEvent
import net.furkanakdemir.websocketsample.util.validator.MessageValidator
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MessageViewModel @Inject constructor(
    private val repository: MessageRepository,
    private val eventRepository: EventRepository,
    private val validator: MessageValidator
) :
    ViewModel() {

    private val _messageLiveData = MutableLiveData<Result>()
    val messageLiveData: LiveData<Result>
        get() = _messageLiveData

    private val _titleLiveData = MutableLiveData<String>()
    val titleLiveData: LiveData<String>
        get() = _titleLiveData

    private val _snackbarLiveData = SingleLiveEvent<String>()
    val snackbarLiveData: LiveData<String>
        get() = _snackbarLiveData

    private val _updateLiveData = SingleLiveEvent<Message>()
    val updateLiveData: LiveData<Message>
        get() = _updateLiveData


    fun getMessages() {
        viewModelScope.launch {
            when (val messageResult = repository.getMessages()) {
                is Result.Success<*> -> {
                    _messageLiveData.value = Result.Success(messageResult.data as List<Message>)
                }

                is Result.Failure -> {
                    _messageLiveData.value = messageResult
                }
            }
        }
    }

    fun sendEvent(text: String) {
        eventRepository.send(text)
    }

    fun getEvents() {

        _titleLiveData.value = TITLE_LOGGED_IN

        eventRepository.register()

        GlobalScope.launch(Dispatchers.IO) {
            // will get dispatched to DefaultDispatcher
            val channel = eventRepository.getMessages()

            for (socketEvent in channel) {
                processMessage(socketEvent)
            }
        }
    }

    private fun processMessage(socketEvent: WebSocketEventRepository.WebSocketEvent) {
        when (socketEvent) {
            is WebSocketEventRepository.WebSocketEvent.Connected -> {
                _snackbarLiveData.postValue("Connected")
            }

            is WebSocketEventRepository.WebSocketEvent.Disconnected -> {
                _snackbarLiveData.postValue("Disconnected")
            }

            is WebSocketEventRepository.WebSocketEvent.Message -> {

                when (val message = socketEvent.message) {
                    "LOGOUT" -> {
                        _titleLiveData.postValue(TITLE_LOGGED_OUT)
                        _snackbarLiveData.postValue("LOGGED OUT")
                    }
                    "LOGIN" -> {
                        _titleLiveData.postValue(TITLE_LOGGED_IN)
                        _snackbarLiveData.postValue("LOGGED IN")
                    }
                    else -> {
                        println("Message Received $message")
                        processUpdateMessage(message)
                    }
                }
            }
        }
    }

    private fun processUpdateMessage(message: String) {

        if (validator.isValidMessage(message)) {
            val result = _messageLiveData.value

            if (result is Result.Success<*>) {
                val messages = ArrayList((result.data as MutableList<Message>).map { it.copy() })

                messages.find {
                    it.id.toString() == message.substringBefore("-")
                }?.let {
                    it.name = message.substringAfter("-")
                }

                _messageLiveData.postValue(Result.Success(messages))
            }
        } else {
            Timber.w("Invalid message: $message")
        }
    }

    override fun onCleared() {
        super.onCleared()
        eventRepository.unregister()
    }

    companion object {
        private const val TITLE_LOGGED_IN = "Enigma"
        private const val TITLE_LOGGED_OUT = "Logged out"
    }
}
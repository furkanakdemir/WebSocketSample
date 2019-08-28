package net.furkanakdemir.websocketsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.furkanakdemir.websocketsample.data.Message
import net.furkanakdemir.websocketsample.data.MessageRepository
import net.furkanakdemir.websocketsample.data.Result
import javax.inject.Inject

class MessageViewModel @Inject constructor(private val repository: MessageRepository) :
    ViewModel() {

    private val _messageLiveData = MutableLiveData<Result>()
    val messageLiveData: LiveData<Result>
        get() = _messageLiveData


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

}
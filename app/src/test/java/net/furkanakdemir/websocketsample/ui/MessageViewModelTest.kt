package net.furkanakdemir.websocketsample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import net.furkanakdemir.websocketsample.data.MessageRepository
import net.furkanakdemir.websocketsample.network.websocket.EventRepository
import net.furkanakdemir.websocketsample.util.validator.MessageValidator
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MessageViewModelTest {

    private lateinit var messageRepository: MessageRepository
    private lateinit var eventRepository: EventRepository
    private lateinit var validator: MessageValidator
    private lateinit var messageViewModel: MessageViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)

        messageRepository = mock(MessageRepository::class.java)
        eventRepository = mock(EventRepository::class.java)
        validator = mock(MessageValidator::class.java)
        messageViewModel = MessageViewModel(messageRepository, eventRepository, validator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetMessages() = runBlockingTest {
        messageViewModel.getMessages()
        verify(messageRepository).getMessages()
    }

    @Test
    fun sendValidMessage() {

        messageViewModel.sendEvent(TEST_MESSAGE)

        verify(eventRepository).send(TEST_MESSAGE)
    }

    @Test
    fun sendInvalidMessage() {

        messageViewModel.sendEvent(TEST_MESSAGE)

        verify(eventRepository).send(TEST_MESSAGE)
    }

    @Test
    fun getEvents() {

        messageViewModel.getEvents()

        verify(eventRepository).register()
    }

    @Test
    fun onExit() {

        messageViewModel.onCleared()

        verify(eventRepository).unregister()
    }

    companion object {

        private const val TEST_MESSAGE = "1-FOO"
    }
}
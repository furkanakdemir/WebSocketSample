package net.furkanakdemir.websocketsample.ui

import dagger.Binds
import dagger.Module
import net.furkanakdemir.websocketsample.data.DomainMapper
import net.furkanakdemir.websocketsample.data.Mapper
import net.furkanakdemir.websocketsample.data.Message
import net.furkanakdemir.websocketsample.data.MessageRepository
import net.furkanakdemir.websocketsample.data.RemoteResponse
import net.furkanakdemir.websocketsample.data.WebSocketMessageRepository
import net.furkanakdemir.websocketsample.network.NetworkModule
import net.furkanakdemir.websocketsample.network.websocket.EventRepository
import net.furkanakdemir.websocketsample.network.websocket.WebSocketEventRepository
import net.furkanakdemir.websocketsample.util.validator.MessageValidator
import net.furkanakdemir.websocketsample.util.validator.WebSocketMessageValidator

@Module(includes = [NetworkModule::class])
abstract class MessageModule {

    @Binds
    abstract fun bindDomainMapper(mapper: DomainMapper):
            Mapper<RemoteResponse.MessageRaw, Message>

    @Binds
    abstract fun bindMessageRepository(repository: WebSocketMessageRepository):
            MessageRepository

    @Binds
    abstract fun bindEventRepository(repository: WebSocketEventRepository):
            EventRepository

    @Binds
    abstract fun bindMessageValidator(validator: WebSocketMessageValidator):
            MessageValidator
}

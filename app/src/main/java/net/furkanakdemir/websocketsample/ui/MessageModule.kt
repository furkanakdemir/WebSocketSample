package net.furkanakdemir.websocketsample.ui

import dagger.Binds
import dagger.Module
import net.furkanakdemir.websocketsample.data.*
import net.furkanakdemir.websocketsample.network.NetworkModule
import net.furkanakdemir.websocketsample.network.websocket.EventRepository
import net.furkanakdemir.websocketsample.network.websocket.WebSocketEventRepository

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

}
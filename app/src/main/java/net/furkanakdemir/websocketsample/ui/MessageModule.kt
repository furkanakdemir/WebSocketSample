package net.furkanakdemir.websocketsample.ui

import dagger.Binds
import dagger.Module
import net.furkanakdemir.websocketsample.data.*
import net.furkanakdemir.websocketsample.network.NetworkModule

@Module(includes = [NetworkModule::class])
abstract class MessageModule {

    @Binds
    abstract fun provideDomainMapper(mapper: DomainMapper):
            Mapper<RemoteResponse.MessageRaw, Message>


    @Binds
    abstract fun provideMessageRepository(repository: WebSocketMessageRepository):
            MessageRepository

}
package net.furkanakdemir.websocketsample.data

import net.furkanakdemir.websocketsample.network.MessageService
import javax.inject.Inject

class WebSocketMessageRepository @Inject constructor(
    private val messageService: MessageService,
    private val mapper: @JvmSuppressWildcards Mapper<RemoteResponse.MessageRaw, Message>
) : MessageRepository {
    override suspend fun getMessages(): Result {

        return try {
            val x = messageService.getMessages().data
                ?.map {
                    mapper.map(it)
                }

            Result.Success(x)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}
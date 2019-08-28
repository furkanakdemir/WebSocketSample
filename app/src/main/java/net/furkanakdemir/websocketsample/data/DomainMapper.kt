package net.furkanakdemir.websocketsample.data

import javax.inject.Inject

class DomainMapper @Inject constructor() : Mapper<RemoteResponse.MessageRaw, Message> {
    override fun map(remote: RemoteResponse.MessageRaw): Message {
        return with(remote) { Message(id, name) }
    }
}
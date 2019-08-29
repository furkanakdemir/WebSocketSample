package net.furkanakdemir.websocketsample.data

import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    @SerializedName("data") val data: List<MessageRaw>? = emptyList()
) {
    data class MessageRaw(
        @SerializedName("id") val id: Int? = -1,
        @SerializedName("name") val name: String? = ""
    )
}

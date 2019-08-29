package net.furkanakdemir.websocketsample.util.validator

import java.util.regex.Pattern
import javax.inject.Inject

class WebSocketMessageValidator @Inject constructor() : MessageValidator {
    override fun isValidMessage(message: String): Boolean {
        return Pattern.compile("\\d+-\\w+").matcher(message).matches()
    }
}
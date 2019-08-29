package net.furkanakdemir.websocketsample.util.validator

interface MessageValidator {
    fun isValidMessage(message: String): Boolean
}

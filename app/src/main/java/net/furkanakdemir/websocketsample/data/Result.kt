package net.furkanakdemir.websocketsample.data

sealed class Result {
    object Loading : Result()
    class Success<T>(val data: T) : Result()
    class Failure(val throwable: Throwable = IllegalStateException()) : Result()
}

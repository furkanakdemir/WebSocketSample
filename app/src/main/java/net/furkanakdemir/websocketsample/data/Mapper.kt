package net.furkanakdemir.websocketsample.data

interface Mapper<in Input, out Output> {

    fun map(remote: Input): Output
}
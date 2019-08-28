package net.furkanakdemir.websocketsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.furkanakdemir.websocketsample.WebSocketApplication
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: WebSocketApplication): Context = application
}

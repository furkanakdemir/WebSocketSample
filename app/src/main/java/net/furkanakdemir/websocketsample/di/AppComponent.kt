package net.furkanakdemir.websocketsample.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.furkanakdemir.websocketsample.WebSocketApplication
import net.furkanakdemir.websocketsample.network.NetworkModule
import net.furkanakdemir.websocketsample.ui.MessageModule
import net.furkanakdemir.websocketsample.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        AppModule::class, ActivityBuilderModule::class, ViewModelModule::class,
        NetworkModule::class, MessageModule::class]
)
interface AppComponent : AndroidInjector<WebSocketApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<WebSocketApplication>
}

package net.furkanakdemir.websocketsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.furkanakdemir.websocketsample.ui.MessageViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MessageViewModel::class)
    abstract fun bindMessageViewModel(messageViewModel: MessageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WebSocketViewModelFactory): ViewModelProvider.Factory
}

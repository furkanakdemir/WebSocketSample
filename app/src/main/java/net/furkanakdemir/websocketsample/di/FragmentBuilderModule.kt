package net.furkanakdemir.websocketsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.websocketsample.ui.MessageListFragment

@ExperimentalCoroutinesApi
@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun bindMessageListFragment(): MessageListFragment
}

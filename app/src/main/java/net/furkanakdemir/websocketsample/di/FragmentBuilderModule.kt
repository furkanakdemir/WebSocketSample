package net.furkanakdemir.websocketsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.furkanakdemir.websocketsample.ui.MessageListFragment

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun bindMessageListFragment(): MessageListFragment
}

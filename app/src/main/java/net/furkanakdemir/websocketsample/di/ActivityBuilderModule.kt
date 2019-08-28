package net.furkanakdemir.websocketsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.furkanakdemir.websocketsample.network.NetworkModule
import net.furkanakdemir.websocketsample.ui.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, NetworkModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}

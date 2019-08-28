package net.furkanakdemir.websocketsample

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import net.furkanakdemir.websocketsample.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class WebSocketApplication : DaggerApplication() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<DaggerApplication>

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        Timber.uprootAll()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}

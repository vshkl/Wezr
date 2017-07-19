package by.vshkl.android.wezr

import android.app.Application
import android.content.Context
import by.vshkl.android.wezr.injection.component.ApplicationComponent
import by.vshkl.android.wezr.injection.component.DaggerApplicationComponent
import by.vshkl.android.wezr.injection.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class Application : Application() {

    internal var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            LeakCanary.install(this)
        }

    }

    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return mApplicationComponent!!
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    companion object {
        operator fun get(context: Context): by.vshkl.android.wezr.Application {
            return context.applicationContext as by.vshkl.android.wezr.Application
        }
    }

}

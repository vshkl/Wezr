package by.vshkl.android.wezr

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import by.vshkl.android.wezr.data.local.WeatherDatabase
import by.vshkl.android.wezr.injection.component.ApplicationComponent
import by.vshkl.android.wezr.injection.component.DaggerApplicationComponent
import by.vshkl.android.wezr.injection.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class Application : Application() {

    internal var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, WeatherDatabase::class.java, "weather_db").build()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            LeakCanary.install(this)
        }

    }

    var component: ApplicationComponent
        get() {
            if (applicationComponent == null) {
                applicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return applicationComponent!!
        }
        set(applicationComponent) {
            this.applicationComponent = applicationComponent
        }

    companion object {
        operator fun get(context: Context): by.vshkl.android.wezr.Application {
            return context.applicationContext as by.vshkl.android.wezr.Application
        }

        var database: WeatherDatabase? = null
    }

}

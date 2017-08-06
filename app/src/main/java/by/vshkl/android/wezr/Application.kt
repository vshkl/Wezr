package by.vshkl.android.wezr

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import by.vshkl.android.wezr.data.local.WeatherDatabase
import by.vshkl.android.wezr.injection.component.ApplicationComponent
import by.vshkl.android.wezr.injection.component.DaggerApplicationComponent
import by.vshkl.android.wezr.injection.module.ApplicationModule
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import com.squareup.leakcanary.LeakCanary
import net.danlew.android.joda.JodaTimeAndroid

class Application : Application() {

    internal var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        BigImageViewer.initialize(GlideImageLoader.with(this))
        database = Room.databaseBuilder(this, WeatherDatabase::class.java, "weather_db").build()
        if (BuildConfig.DEBUG) {
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

        lateinit var database: WeatherDatabase
    }

}

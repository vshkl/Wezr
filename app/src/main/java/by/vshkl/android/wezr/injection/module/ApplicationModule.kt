package by.vshkl.android.wezr.injection.module

import android.app.Application
import android.content.Context
import by.vshkl.android.wezr.data.remote.WeatherService
import by.vshkl.android.wezr.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    internal fun providesApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun providesContext(): Context {
        return application
    }

    @Provides
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    internal fun providesWeatherService(okHttpClient: OkHttpClient): WeatherService {
        return WeatherService(okHttpClient)
    }

}

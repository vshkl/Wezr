package by.vshkl.android.wezr.injection.module

import android.app.Application
import android.content.Context
import by.vshkl.android.wezr.data.remote.BoilerplateService
import by.vshkl.android.wezr.data.remote.BoilerplateServiceFactory
import by.vshkl.android.wezr.injection.ApplicationContext
import dagger.Module
import dagger.Provides
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
    @Singleton
    internal fun providesBoilerplateService(): BoilerplateService {
        return BoilerplateServiceFactory.makeBoilerplateService()
    }

}

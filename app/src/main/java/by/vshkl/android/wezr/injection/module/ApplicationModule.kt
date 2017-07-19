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
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun providesApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun providesContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun providesBoilerplateService(): BoilerplateService {
        return BoilerplateServiceFactory.makeBoilerplateService()
    }

}

package by.vshkl.android.wezr.injection.component

import android.app.Application
import android.content.Context
import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.injection.ApplicationContext
import by.vshkl.android.wezr.injection.module.ApplicationModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    @ApplicationContext fun context(): Context
    fun application(): Application
    fun dataManager(): DataManager
    fun okHttpClient(): OkHttpClient
}

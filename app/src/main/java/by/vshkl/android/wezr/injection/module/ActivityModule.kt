package by.vshkl.android.wezr.injection.module

import android.app.Activity
import android.content.Context
import by.vshkl.android.wezr.injection.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return activity
    }

}

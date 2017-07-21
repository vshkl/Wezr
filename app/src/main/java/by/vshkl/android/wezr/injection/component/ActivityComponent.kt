package by.vshkl.android.wezr.injection.component

import by.vshkl.android.wezr.injection.PerActivity
import by.vshkl.android.wezr.injection.module.ActivityModule
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.ui.forecast.ForecastActivity
import by.vshkl.android.wezr.ui.radar.RadarActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(forecastActivity: ForecastActivity)
    fun inject(radarActivity: RadarActivity)
}

package by.vshkl.android.wezr.injection.component

import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.injection.component.ActivityComponent
import by.vshkl.android.wezr.injection.component.ApplicationComponent
import by.vshkl.android.wezr.injection.module.ActivityModule
import dagger.Component

@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
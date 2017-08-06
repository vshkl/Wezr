package by.vshkl.android.wezr.ui.base

import android.os.Bundle
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.ButterKnife
import by.vshkl.android.wezr.Application
import by.vshkl.android.wezr.injection.component.ActivityComponent
import by.vshkl.android.wezr.injection.component.ConfigPersistentComponent
import by.vshkl.android.wezr.injection.component.DaggerConfigPersistentComponent
import by.vshkl.android.wezr.injection.module.ActivityModule
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    private var activityComponent: ActivityComponent? = null
    private var activityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        ButterKnife.bind(this)

        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(activityId) == null) {
            info("Creating new ConfigPersistentComponent $activityId")
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application[this].component)
                    .build()
            sComponentsArray.put(activityId, configPersistentComponent)
        } else {
            info("Reusing ConfigPersistentComponent id=$activityId")
            configPersistentComponent = sComponentsArray.get(activityId)
        }

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        activityComponent!!.inject(this)
    }

    abstract val layout: Int

    companion object {
        private val KEY_ACTIVITY_ID: String = "KEY_ACTIVITY_ID"
        private val NEXT_ID: AtomicLong = AtomicLong(0)
        private val sComponentsArray: LongSparseArray<ConfigPersistentComponent> = LongSparseArray()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (isChangingConfigurations) {
            info("Clearing ConfigPersistentComponent id=$activityId")
            sComponentsArray.remove(activityId)
        }
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun activityComponent(): ActivityComponent {
        return activityComponent!!
    }

}

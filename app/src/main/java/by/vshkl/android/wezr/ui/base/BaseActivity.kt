package by.vshkl.android.wezr.ui.base

import android.os.Bundle
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.ButterKnife
import by.vshkl.android.wezr.injection.component.ActivityComponent
import by.vshkl.android.wezr.injection.component.ConfigPersistentComponent
import by.vshkl.android.wezr.injection.module.ActivityModule
import by.vshkl.android.wezr.Application
import by.vshkl.android.wezr.injection.component.DaggerConfigPersistentComponent
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity() {

    private var mActivityComponent: ActivityComponent? = null
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        ButterKnife.bind(this)

        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(mActivityId) == null) {
            Timber.i("Creating new ConfigPersistentComponent $mActivityId")
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application[this].component)
                    .build()
            sComponentsArray.put(mActivityId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=$mActivityId")
            configPersistentComponent = sComponentsArray.get(mActivityId)
        }

        mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        mActivityComponent!!.inject(this)
    }

    abstract val layout: Int

    companion object {
        private val KEY_ACTIVITY_ID: String = "KEY_ACTIVITY_ID"
        private val NEXT_ID: AtomicLong = AtomicLong(0)
        private val sComponentsArray: LongSparseArray<ConfigPersistentComponent> = LongSparseArray()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=$mActivityId")
            sComponentsArray.remove(mActivityId)
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
        return mActivityComponent!!
    }

}

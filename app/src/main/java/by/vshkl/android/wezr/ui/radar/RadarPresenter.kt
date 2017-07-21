package by.vshkl.android.wezr.ui.radar

import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.ui.base.BasePresenter
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ConfigPersistent
class RadarPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<RadarView>() {

    private var radarView: RadarView? = null
    private var disposable: Disposable? = null

    override fun attachView(mvpView: RadarView) {
        radarView = mvpView
    }

    override fun detachView() {
        radarView = null
        disposable?.dispose()
    }
}
package by.vshkl.android.wezr.ui.radar

import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class RadarPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<RadarView>() {

    private var radarView: RadarView? = null
    private var disposable: Disposable? = null
    private var radarImageUrl: String? = null

    override fun attachView(mvpView: RadarView) {
        radarView = mvpView
    }

    override fun detachView() {
        radarView = null
        disposable?.dispose()
    }

    fun getRadarData() {
        disposable = dataManager.getRadarDara()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { radarImageUrl ->
                    this.radarImageUrl = radarImageUrl
                    radarView?.showRadarImage(radarImageUrl)
                    radarView?.hideProgressIndicator()
                }
    }

    fun shareRadarImage() {
        radarView?.shareRadarImage(this.radarImageUrl ?: "")
    }
}
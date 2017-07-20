package by.vshkl.android.wezr.ui.main

import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject constructor(private val dataManager: DataManager) : BasePresenter<MainView>() {

    private var mainView: MainView? = null
    private var disposable: Disposable? = null

    override fun attachView(mvpView: MainView) {
        mainView = mvpView
    }

    override fun detachView() {
        mainView = null
        disposable?.dispose()
    }

    fun getWeatherData(cityCode: Int) {
        disposable = dataManager.getWeatherData(cityCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println(it)
                }
    }

}

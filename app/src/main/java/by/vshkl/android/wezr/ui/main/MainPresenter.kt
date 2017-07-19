package by.vshkl.android.wezr.ui.main

import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.ui.base.BasePresenter
import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject constructor(private val mDataManager: DataManager) : BasePresenter<MainView>() {

    private var mainView: MainView? = null

    override fun attachView(mvpView: MainView) {
        mainView = mvpView
    }

}

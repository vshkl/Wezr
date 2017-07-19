package by.vshkl.android.wezr.ui.main

import android.os.Bundle
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.R
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mMainPresenter.attachView(this)
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mMainPresenter.detachView()
    }

    override fun showList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

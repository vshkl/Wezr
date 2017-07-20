package by.vshkl.android.wezr.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var mainPresenter: MainPresenter

    @BindView(R.id.rv_weather_list) lateinit var rvWeatherList: RecyclerView

    override val layout: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)
        mainPresenter.getWeatherData(26850)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

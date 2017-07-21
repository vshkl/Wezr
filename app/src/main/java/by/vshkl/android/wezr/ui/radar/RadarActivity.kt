package by.vshkl.android.wezr.ui.radar

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.base.BaseActivity
import com.github.piasy.biv.view.BigImageView
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrPosition
import javax.inject.Inject

class RadarActivity : BaseActivity(), RadarView {

    @Inject lateinit var radarPresenter: RadarPresenter

    @BindView(R.id.iv_radar) lateinit var ivRadar: BigImageView

    override val layout: Int get() = R.layout.activity_radar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        activityComponent().inject(this)
        initializeSlider()
        radarPresenter.attachView(this)
        radarPresenter.getRadarData()
    }

    override fun onDestroy() {
        super.onDestroy()
        radarPresenter.detachView()
    }

    override fun showRadarImage(radarImageUrl: String) {
        ivRadar.showImage(Uri.parse(radarImageUrl))
    }

    private fun initializeSlider() {
        Slidr.attach(this, SlidrConfig.Builder()
                .position(SlidrPosition.VERTICAL)
                .sensitivity(0.1F)
                .scrimStartAlpha(1F)
                .scrimEndAlpha(0.1F)
                .velocityThreshold(0.5F)
                .distanceThreshold(0.25F)
                .build())
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, RadarActivity::class.java)
    }
}
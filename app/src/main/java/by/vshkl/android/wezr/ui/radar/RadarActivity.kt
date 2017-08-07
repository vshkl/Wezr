package by.vshkl.android.wezr.ui.radar

import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.ViewDragHelper
import android.view.View
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.util.NavigationUtils
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrListener
import com.r0adkll.slidr.model.SlidrPosition
import kotlinx.android.synthetic.main.activity_radar.*
import javax.inject.Inject

class RadarActivity : BaseActivity(), RadarView, SlidrListener {

    @Inject lateinit var radarPresenter: RadarPresenter

    override val layout: Int get() = R.layout.activity_radar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        initializeSlider()
        radarPresenter.attachView(this)
        radarPresenter.getRadarData()
        ivShare.setOnClickListener { radarPresenter.shareRadarImage() }
    }

    override fun onDestroy() {
        super.onDestroy()
        radarPresenter.detachView()
    }

    override fun showProgressIndicator() {
        ivRadar.visibility = View.GONE
        pbProgress.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        pbProgress.visibility = View.GONE
        ivRadar.visibility = View.VISIBLE
    }

    override fun showRadarImage(radarImageUrl: String) {
        ivRadar.showImage(Uri.parse(radarImageUrl))
    }

    override fun shareRadarImage(radarImageUrl: String) {
        NavigationUtils.shareImageLink(this, radarImageUrl)
    }

    override fun onSlideClosed() = Unit

    override fun onSlideStateChanged(state: Int) {
        if (state == ViewDragHelper.STATE_DRAGGING || state == ViewDragHelper.STATE_SETTLING) {
            ivShare.visibility = View.GONE
        } else {
            ivShare.visibility = View.VISIBLE
        }
    }

    override fun onSlideChange(percent: Float) = Unit

    override fun onSlideOpened() = Unit

    private fun initializeSlider() {
        Slidr.attach(this, SlidrConfig.Builder()
                .position(SlidrPosition.VERTICAL)
                .sensitivity(0.1F)
                .scrimStartAlpha(1F)
                .scrimEndAlpha(0.1F)
                .velocityThreshold(0.5F)
                .distanceThreshold(0.25F)
                .listener(this)
                .build())
    }
}
package by.vshkl.android.wezr.ui.radar

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.ViewDragHelper
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.util.NavigationUtils
import com.github.piasy.biv.view.BigImageView
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrListener
import com.r0adkll.slidr.model.SlidrPosition
import javax.inject.Inject

class RadarActivity : BaseActivity(), RadarView, SlidrListener {

    @Inject lateinit var radarPresenter: RadarPresenter

    @BindView(R.id.iv_radar) lateinit var ivRadar: BigImageView
    @BindView(R.id.iv_share) lateinit var ivShare: ImageView

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

    override fun shareRadarImage(radarImageUrl: String) {
        NavigationUtils.shareImageLink(this, radarImageUrl)
    }

    @OnClick(R.id.iv_share)
    fun onShareClicked() {
        radarPresenter.shareRadarImage()
    }

    override fun onSlideClosed() {
    }

    override fun onSlideStateChanged(state: Int) {
        if (state == ViewDragHelper.STATE_DRAGGING || state == ViewDragHelper.STATE_SETTLING) {
            ivShare.visibility = View.GONE
        } else {
            ivShare.visibility = View.VISIBLE
        }
    }

    override fun onSlideChange(percent: Float) {
    }

    override fun onSlideOpened() {
    }

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

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, RadarActivity::class.java)
    }
}
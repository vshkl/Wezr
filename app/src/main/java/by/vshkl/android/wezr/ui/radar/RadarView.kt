package by.vshkl.android.wezr.ui.radar

import by.vshkl.android.wezr.ui.base.MvpView

interface RadarView : MvpView {

    fun showRadarImage(radarImageUrl: String)

    fun shareRadarImage(radarImageUrl: String)
}
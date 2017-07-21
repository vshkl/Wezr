package by.vshkl.android.wezr.util

import android.content.Context
import by.vshkl.android.wezr.ui.radar.RadarActivity

object NavigationUtils {

    fun navigateToRadar(context: Context) {
        context.startActivity(RadarActivity.newIntent(context))
    }
}
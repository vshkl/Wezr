package by.vshkl.android.wezr.util

import android.content.Context
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.radar.RadarActivity
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity

object NavigationUtils {

    fun navigateToRadar(context: Context) = context.startActivity<RadarActivity>()

    fun shareImageLink(context: Context, imageLink: String)
            = context.share(imageLink, context.getString(R.string.share_action_text))
}
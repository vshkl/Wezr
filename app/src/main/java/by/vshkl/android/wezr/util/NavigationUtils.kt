package by.vshkl.android.wezr.util

import android.content.Context
import android.content.Intent
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.ui.radar.RadarActivity

object NavigationUtils {

    fun navigateToRadar(context: Context) {
        context.startActivity(RadarActivity.newIntent(context))
    }

    fun shareImageLink(context: Context, imageLink: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, imageLink)
        intent.type = "text/plain"
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_action_text)))
    }
}
package by.vshkl.android.wezr.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val context: Context) {

    fun getNetworkInfo(): NetworkInfo?
            = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    fun isConnected(): Boolean = getNetworkInfo()?.isConnected ?: false
}
package by.vshkl.android.wezr.data.remote

import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.util.ParserUtils
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherService @Inject constructor(private val client: OkHttpClient) {

    val WEATHER_REQUEST_URL = "http://meteoinfo.by/wrf15/?city="

    fun getWeatherData(cityCode: Int): Single<List<Weather>> = Single.create {
        val request = Request.Builder().url("$WEATHER_REQUEST_URL$cityCode").build()
        val response = client.newCall(request).execute()
        it.onSuccess(ParserUtils.parseHtmlPage(response.body().string()))
    }
}
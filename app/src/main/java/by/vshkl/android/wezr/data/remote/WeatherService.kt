package by.vshkl.android.wezr.data.remote

import by.vshkl.android.wezr.data.model.City
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.util.JsonUtils
import by.vshkl.android.wezr.util.ParserUtils
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherService @Inject constructor(private val client: OkHttpClient) {

    val CITIES_LIST_URL = "https://firebasestorage.googleapis.com/v0/b/bashq-29aa3.appspot.com/o/cities.json?alt=media&token=e804ae33-abaa-4200-b91a-546d380de6de"
    val WEATHER_REQUEST_URL = "http://meteoinfo.by/wrf15/?city="
    val RADAR_REQUEST_URL = "http://meteoinfo.by/radar/?q=UMMN"

    fun getCities(): Single<List<City>> = Single.create {
        val request = Request.Builder().url(CITIES_LIST_URL).build()
        val response = client.newCall(request).execute()
        it.onSuccess(JsonUtils.cityListFromJsonArray(response.body()?.string()))
    }

    fun getWeatherData(cityCode: Int): Single<List<Weather>> = Single.create {
        val request = Request.Builder().url("$WEATHER_REQUEST_URL$cityCode").build()
        val response = client.newCall(request).execute()
        it.onSuccess(ParserUtils.parseForecastPage(response.body()?.string() ?: ""))
    }

    fun getLatestRadarImage(): Single<String> = Single.create {
        val request = Request.Builder().url(RADAR_REQUEST_URL).build()
        val response = client.newCall(request).execute()
        it.onSuccess(ParserUtils.parseRadarPage(response.body()?.string() ?: ""))
    }
}
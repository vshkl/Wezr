package by.vshkl.android.wezr.data.remote

import by.vshkl.android.wezr.data.models.Weather
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherService @Inject constructor(private val client: OkHttpClient) {

    fun getWeatherData(cityCode: Int): Observable<List<Weather>> {
        return Observable.create {
            val request = Request.Builder().url("http://meteoinfo.by/wrf15/?city=" + cityCode).build()

            val response = client.newCall(request).execute()

            println(response.body().string())
        }
    }
}
package by.vshkl.android.wezr.data.models

import org.joda.time.DateTime

data class Weather(val time: DateTime,
                   val tempLow: Int,
                   val tempHigh: Int,
                   val imageUrl: String,
                   val weatherDescription: String,
                   val wind: String,
                   val pressure: String,
                   val humidity: String)
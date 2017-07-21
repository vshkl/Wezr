package by.vshkl.android.wezr.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
        @PrimaryKey(autoGenerate = false)
        var time: Long = System.currentTimeMillis(),
        @ColumnInfo(name = "temperature_low")
        var tempLow: Int = 0,
        @ColumnInfo(name = "temperature_high")
        var tempHigh: Int = 0,
        @ColumnInfo(name = "image_url")
        var imageUrl: String = "",
        @ColumnInfo(name = "weather_description")
        var weatherDescription: String = "",
        @ColumnInfo(name = "wind")
        var wind: String = "",
        @ColumnInfo(name = "pressure")
        var pressure: String = "",
        @ColumnInfo(name = "humidity")
        var humidity: String = ""
)
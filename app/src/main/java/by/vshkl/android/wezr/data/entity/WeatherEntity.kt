package by.vshkl.android.wezr.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "weather")
data class WeatherEntity(
        @PrimaryKey(autoGenerate = true) val uid: Long,
        @ColumnInfo(name = "time") val time: DateTime,
        @ColumnInfo(name = "temperature_low") val tempLow: Int,
        @ColumnInfo(name = "temperature_high") val tempHigh: Int,
        @ColumnInfo(name = "image_url") val imageUrl: String,
        @ColumnInfo(name = "weather_description") val weatherDescription: String,
        @ColumnInfo(name = "wind") val wind: String,
        @ColumnInfo(name = "pressure") val pressure: String,
        @ColumnInfo(name = "humidity") val humidity: String
)
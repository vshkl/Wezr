package by.vshkl.android.wezr.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import by.vshkl.android.wezr.data.entity.WeatherEntity

@Database(version = 1, entities = arrayOf(WeatherEntity::class))
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
package by.vshkl.android.wezr.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import by.vshkl.android.wezr.data.entity.CityEntity
import by.vshkl.android.wezr.data.entity.WeatherEntity

@Database(version = 1, entities = arrayOf(CityEntity::class, WeatherEntity::class))
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao
}
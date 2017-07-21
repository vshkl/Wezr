package by.vshkl.android.wezr.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.vshkl.android.wezr.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeatherEntities(): WeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherEntity(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather")
    fun deteteEntities()
}
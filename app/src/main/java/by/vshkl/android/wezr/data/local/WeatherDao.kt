package by.vshkl.android.wezr.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.vshkl.android.wezr.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getAll(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weatherEntityList: List<WeatherEntity>)

    @Query("DELETE FROM weather")
    fun deleteAll()
}
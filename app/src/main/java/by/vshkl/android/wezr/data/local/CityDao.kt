package by.vshkl.android.wezr.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.vshkl.android.wezr.data.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getAll(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityEntity: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cityEntityList: List<CityEntity>)

    @Query("DELETE FROM cities")
    fun deleteAll()
}
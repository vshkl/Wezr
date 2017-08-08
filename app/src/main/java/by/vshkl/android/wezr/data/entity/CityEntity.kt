package by.vshkl.android.wezr.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0,
        @ColumnInfo(name = "city_name")
        var name: String = ""
)
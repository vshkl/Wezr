package by.vshkl.android.wezr.data.mapper

import by.vshkl.android.wezr.data.entity.CityEntity
import by.vshkl.android.wezr.data.model.City

object CityMapper {

    fun transform(cityEntity: CityEntity): City = City(
            cityEntity.id,
            cityEntity.name
    )

    fun transform(cityEntityList: List<CityEntity>): List<City> = cityEntityList.map { transform(it) }

}
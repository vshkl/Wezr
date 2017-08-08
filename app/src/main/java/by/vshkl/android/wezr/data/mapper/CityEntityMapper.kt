package by.vshkl.android.wezr.data.mapper

import by.vshkl.android.wezr.data.entity.CityEntity
import by.vshkl.android.wezr.data.model.City

object CityEntityMapper {

    fun transform(city: City): CityEntity = CityEntity(
            city.id,
            city.name
    )

    fun transform(cityList: List<City>): List<CityEntity> = cityList.map { transform(it) }

}

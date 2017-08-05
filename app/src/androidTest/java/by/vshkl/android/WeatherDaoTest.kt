package by.vshkl.android

import android.support.test.runner.AndroidJUnit4
import by.vshkl.android.wezr.MockModelFabric
import by.vshkl.android.wezr.data.mapper.WeatherEntityMapper
import by.vshkl.android.wezr.data.mapper.WeatherMapper
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest : WeatherDatabaseTest() {

    @Test
    @Throws(Exception::class)
    fun insertAndGetWeatherTest() {
        val weatherIn = MockModelFabric.newWeather(0)

        weatherDao.insert(WeatherEntityMapper.transform(weatherIn))
        val weatherOut = WeatherMapper.transform(weatherDao.getAll())

        assertEquals(1, weatherOut.size)
        assertEquals(weatherIn, weatherOut.first())
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWeatherListTest() {
        val weatherListIn = MockModelFabric.newWeatherList(10)

        weatherDao.insertAll(WeatherEntityMapper.transform(weatherListIn))
        val weatherListOut = WeatherMapper.transform(weatherDao.getAll())

        assertArrayEquals(weatherListIn.toTypedArray(), weatherListOut.toTypedArray())
    }

    @Test
    @Throws(Exception::class)
    fun deleteWeatherTest() {
        val weatherListIn = MockModelFabric.newWeatherList(10)

        weatherDao.insertAll(WeatherEntityMapper.transform(weatherListIn))
        val weatherListOutBefore = WeatherMapper.transform(weatherDao.getAll())

        weatherDao.deleteAll()
        val weatherListOutAfter = WeatherMapper.transform(weatherDao.getAll())

        assertEquals(10, weatherListOutBefore.size)
        assertEquals(0, weatherListOutAfter.size)
    }
}
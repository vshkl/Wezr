package by.vshkl.android

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import by.vshkl.android.wezr.data.local.WeatherDao
import by.vshkl.android.wezr.data.local.WeatherDatabase
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class WeatherDatabaseTest {

    lateinit var database: WeatherDatabase
    lateinit var weatherDao: WeatherDao

    @Before
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), WeatherDatabase::class.java)
                .build()
        weatherDao = database.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }
}
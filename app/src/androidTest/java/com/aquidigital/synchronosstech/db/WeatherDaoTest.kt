package com.aquidigital.synchronosstech.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aquidigital.synchronosstech.repository.cache.WeatherEntity
import com.aquidigital.synchronosstech.repository.remote.model.Wind
import com.aquidigital.synchronosstech.util.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest : DbTestClass() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {
        // given
        val weather = weather()
        val sunrise:Long = 1558270584
        val sunset:Long = 1558322007

        // when
        db.weatherDao().insertWeather(weather)

        // then
        val loaded = LiveDataTestUtil.getValue(db.weatherDao().getLocalWeather())
        assertThat(loaded, notNullValue())
        assertThat(loaded.currentTemp, `is`(25.0))
        assertThat(loaded.maxTemp, `is`(32.0))
        assertThat(loaded.minTemp, `is`(21.0))
        assertThat(loaded.humdity, `is`("5"))
        assertThat(loaded.iconCode, `is`("icon"))
        assertThat(loaded.conditionTitle, `is`("hot"))
        assertThat(loaded.sunrise, `is`(sunrise))
        assertThat(loaded.sunset, `is`(sunset))
        assertThat(loaded.country, `is`("Ireland"))
        assertThat(loaded.place, `is`("Derry"))


    }

    private fun weather() = WeatherEntity(
        currentTemp = 25.0,
        maxTemp = 32.0,
        minTemp = 21.0,
        humdity = "5",
        iconCode = "icon",
        conditionTitle = "hot",
        sunrise = 1558270584,
        sunset = 1558322007,
        country = "Ireland",
        wind = Wind(1.0, 1.0),
        place = "Derry"
    )
}

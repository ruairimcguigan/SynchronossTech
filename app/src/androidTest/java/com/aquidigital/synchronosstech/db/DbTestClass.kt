package com.aquidigital.synchronosstech.db

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.aquidigital.synchronosstech.repository.cache.WeatherDb
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DbTestClass {
    @Rule
    @JvmField
    val countingAppExecutors = CountingTaskExecutorRule()

    lateinit var db: WeatherDb

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WeatherDb::class.java
        ).build()
    }

    @After
    fun tearDown() {
        countingAppExecutors.drainTasks(10, TimeUnit.SECONDS)
        db.close()
    }
}
package com.aquidigital.synchronosstech.repository.remote

import com.aquidigital.synchronosstech.repository.cache.WeatherEntity

class WeatherResult {
    private var results: List<WeatherEntity>? = null

    fun getResults(): List<WeatherEntity>? {
        return results
    }

    fun setResults(results: List<WeatherEntity>) {
        this.results = results
    }
}
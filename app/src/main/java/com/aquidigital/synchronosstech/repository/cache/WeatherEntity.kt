package com.aquidigital.synchronosstech.repository.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aquidigital.synchronosstech.repository.remote.model.Wind

@Entity
data class WeatherEntity (
    @ColumnInfo(name = "current_temp") var currentTemp: Double,
    @ColumnInfo(name = "max_temp") var maxTemp: Double,
    @ColumnInfo(name = "min_temp") var minTemp: Double,
    @ColumnInfo(name = "humidity") var humdity: String,
    @ColumnInfo(name = "icon_code") var iconCode: String,
    @ColumnInfo(name = "condition_title") var conditionTitle: String,
    @ColumnInfo(name = "sunrise") var sunrise: Long,
    @ColumnInfo(name = "sunset") var sunset: Long,
    @ColumnInfo(name = "country") var country: String,
//    @ColumnInfo(name ="wind") var wind: List<Wind>,
    @ColumnInfo(name = "place") var place: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
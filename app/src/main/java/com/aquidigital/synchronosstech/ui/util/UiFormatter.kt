package com.aquidigital.synchronosstech.ui.util

import android.content.res.Resources
import java.lang.Math.toIntExact
import java.time.Instant.ofEpochMilli
import java.time.ZoneOffset
import java.time.ZonedDateTime.ofInstant
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class UiFormatter@Inject constructor() {

    fun epochToDate(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm a")
        return formatter.format(ofInstant(ofEpochMilli(timestamp), ZoneOffset.UTC))
    }

    fun formatWithMeasurementUnit(res: Resources, unit: Int, value: Double): String {
        val roundUp = toIntExact(Math.round(value))
        return String.format(res.getString(unit).toString(), roundUp)
    }
}
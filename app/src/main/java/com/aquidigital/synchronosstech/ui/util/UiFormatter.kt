package com.aquidigital.synchronosstech.ui.util

import android.content.res.Resources
import java.lang.Math.toIntExact
import android.text.format.DateFormat.format
import java.util.*
import java.util.Calendar.getInstance
import javax.inject.Inject


class UiFormatter@Inject constructor() {

    fun epochToDate(timestamp: Long): String {
        val cal = getInstance(Locale.ENGLISH)
        cal.timeInMillis = timestamp * 1000L
        return format("hh:mm:ss", cal).toString()
    }

    fun formatWithMeasurementUnit(res: Resources, unit: Int, value: Double): String {
        val roundUp = toIntExact(Math.round(value))
        return String.format(res.getString(unit).toString(), roundUp)
    }
}
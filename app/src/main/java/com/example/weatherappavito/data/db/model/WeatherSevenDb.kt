package com.example.weatherappavito.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_days_week_table")
data class WeatherSevenDb(
    val datetime: String?,
    @PrimaryKey
    val datetimeEpoch: Int?,
    val tempmax: Double?,
    val tempmin: Double?,
    val temp: Double?,
    val feelslike: Double?,
    val humidity: Double?,
    val icon: String?,
    val windspeed: Double?

    )
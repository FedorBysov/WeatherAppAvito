package com.example.weatherappavito.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_now_day_table")
data class WeatherNowDb(
    val datetime: String?,
    @PrimaryKey
    val datetimeEpoch: Int?,
    val temp: Double?,
    val feelslike: Double?,
    val humidity: Double?,
    val dew: Double?,
    val precip: Double?,
    val precipprob: Double?,
    val snow: Double?,
    val snowdepth: Double?,
    val windgust: Double?,
    val windspeed: Double?,
    val winddir: Double?,
    val pressure: Double?,
    val visibility: Double?,
    val cloudcover: Double?,
    val solarradiation: Double?,
    val uvindex: Double?,
    val conditions: String?,
    val icon: String?,
    val source: String?,
    val sunrise: String?,
    val sunriseEpoch: Int?,
    val sunset: String?,
    val sunsetEpoch: Int?,
    val resolvedAddress: String?,
    val address: String?,

    val minTemp: Double?,
    val maxTemp: Double?

)
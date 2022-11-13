package com.example.weatherappavito.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_hour_day_table")
data class WeatherHourDb(

     val datetime: String?,
    @PrimaryKey
     val datetimeEpoch: Int?,
     val temp: Double?,
     val humidity: Double?,
     val icon: String?
)
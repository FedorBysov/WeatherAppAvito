package com.example.weatherappavito.data.api.model.weatherHourResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDayHourDto(
    @SerializedName("hours")
    @Expose
    val hours: List<WeatherHourDto>? = null
)
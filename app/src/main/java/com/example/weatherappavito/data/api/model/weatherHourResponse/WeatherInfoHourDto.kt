package com.example.weatherappavito.data.api.model.weatherHourResponse

import com.example.weatherappavito.data.api.model.weatherHourResponse.WeatherDayHourDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherInfoHourDto(
    @SerializedName("days")
    @Expose
    val days: List<WeatherDayHourDto>? = null
)
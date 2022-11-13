package com.example.weatherappavito.data.api.model.weatherWeekResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherInfoSevenDaysDto(
    @SerializedName("days")
    @Expose
    val days: List<WeatherSevenDaysDto>? = null,
)
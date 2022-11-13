package com.example.weatherappavito.data.api.model.weatherNowResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherInfoNowDto(

    @SerializedName("address")
    @Expose
    val address: String? = null,

    @SerializedName("currentConditions")
    @Expose
    val currentConditions: WeatherNowDto
)
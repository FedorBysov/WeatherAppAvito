package com.example.weatherappavito.data.api.model.weatherHourResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherHourDto(

    @SerializedName("datetime")
    @Expose
    val datetime: String?,
    @SerializedName("datetimeEpoch")
    @Expose
    val datetimeEpoch: Int?,
    @SerializedName("temp")
    @Expose
    val temp: Double?,
    @SerializedName("humidity")
    @Expose
    val humidity: Double?,
    @SerializedName("icon")
    @Expose
    val icon: String?
)

// Время
// Температура
// Влажность
// Иконка

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/today?unitGroup=metric&include=hours&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
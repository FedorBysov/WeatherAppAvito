package com.example.weatherappavito.data.api.model.weatherWeekResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherSevenDaysDto(

    @SerializedName("datetime")
    @Expose
    val datetime: String?,
    @SerializedName("datetimeEpoch")
    @Expose
    val datetimeEpoch: Int?,
    @SerializedName("tempmax")
    @Expose
    val tempmax: Double?,
    @SerializedName("tempmin")
    @Expose
    val tempmin: Double?,
    @SerializedName("temp")
    @Expose
    val temp: Double?,
    @SerializedName("feelslike")
    @Expose
    val feelslike: Double?,
    @SerializedName("humidity")
    @Expose
    val humidity: Double?,
    @SerializedName("icon")
    @Expose
    val icon: String?,
    @SerializedName("windspeed")
    @Expose
    val windspeed: Double?
)

//температура макс
//температура мин
//температура сейчас
//Как ощущается
//Влажность
//Скорость ветра

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/next7days?unitGroup=metric&include=days&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
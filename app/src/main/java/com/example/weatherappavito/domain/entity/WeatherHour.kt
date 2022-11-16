package com.example.weatherappavito.domain.entity

data class WeatherHour(


    val datetime: String?,
    val datetimeEpoch: Int?,
    val temp: Double?,
    val humidity: Double?,
    val icon: Int
)

// Время
// Температура
// Влажность
// Иконка

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/today?unitGroup=metric&include=hours&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
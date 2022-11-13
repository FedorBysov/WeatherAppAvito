package com.example.weatherappavito.domain.entity

data class WeatherSevenDays(

    val datetime: String?,
    val datetimeEpoch: Int?,
    val tempmax: Double?,
    val tempmin: Double?,
    val temp: Double?,
    val feelslike: Double?,
    val humidity: Double?,
    val windspeed: Double?

    )

//температура макс
//температура мин
//температура сейчас
//Как ощущается
//Влажность
//Скорость ветра

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/next7days?unitGroup=metric&include=days&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
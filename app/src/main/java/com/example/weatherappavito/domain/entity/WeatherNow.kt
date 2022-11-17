package com.example.weatherappavito.domain.entity

data class WeatherNow(

    val datetime: String?,
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
    val icon: Int,
    val source: String?,
    val sunrise: String?,
    val sunriseEpoch: Int?,
    val sunset: String?,
    val sunsetEpoch: Int?,

    val address:String?,

    val minTemp: Double?,
    val maxTemp: Double?
    )

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/today?unitGroup=metric&include=current&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
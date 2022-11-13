package com.example.weatherappavito.data.api.model.weatherNowResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherNowDto(

    @SerializedName("datetime")
    @Expose
    val datetime: String?,
    @SerializedName("datetimeEpoch")
    @Expose
    val datetimeEpoch: Int?,
    @SerializedName("temp")
    @Expose
    val temp: Double?,
    @SerializedName("feelslike")
    @Expose
    val feelslike: Double?,
    @SerializedName("humidity")
    @Expose
    val humidity: Double?,
    @SerializedName("dew")
    @Expose
    val dew: Double?,
    @SerializedName("precip")
    @Expose
    val precip: Double?,
    @SerializedName("precipprob")
    @Expose
    val precipprob: Double?,
    @SerializedName("snow")
    @Expose
    val snow: Double?,
    @SerializedName("snowdepth")
    @Expose
    val snowdepth: Double?,
    @SerializedName("windgust")
    @Expose
    val windgust: Double?,
    @SerializedName("windspeed")
    @Expose
    val windspeed: Double?,
    @SerializedName("winddir")
    @Expose
    val winddir: Double?,
    @SerializedName("pressure")
    @Expose
    val pressure: Double?,
    @SerializedName("visibility")
    @Expose
    val visibility: Double?,
    @SerializedName("cloudcover")
    @Expose
    val cloudcover: Double?,
    @SerializedName("solarradiation")
    @Expose
    val solarradiation: Double?,
    @SerializedName("uvindex")
    @Expose
    val uvindex: Double?,
    @SerializedName("conditions")
    @Expose
    val conditions: String?,
    @SerializedName("icon")
    @Expose
    val icon: String?,
    @SerializedName("source")
    @Expose
    val source: String?,
    @SerializedName("sunrise")
    @Expose
    val sunrise: String?,
    @SerializedName("sunriseEpoch")
    @Expose
    val sunriseEpoch: Int?,
    @SerializedName("sunset")
    @Expose
    val sunset: String?,
    @SerializedName("sunsetEpoch")
    @Expose
    val sunsetEpoch: Int?

    )

// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Saint-Petersburg/today?unitGroup=metric&include=current&key=PDPFUPG89PEE5GXQEKA6VWGDB&contentType=json
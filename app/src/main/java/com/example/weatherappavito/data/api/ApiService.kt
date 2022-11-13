package com.example.weatherappavito.data.api

import com.example.weatherappavito.data.api.model.weatherHourResponse.WeatherDayHourDto
import com.example.weatherappavito.data.api.model.weatherHourResponse.WeatherInfoHourDto
import com.example.weatherappavito.data.api.model.weatherNowResponse.WeatherInfoNowDto
import com.example.weatherappavito.data.api.model.weatherWeekResponse.WeatherInfoSevenDaysDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Прогноз погоды за неделю
    @GET("{location}/next7days?")
    suspend fun getWeatherInfoOneWeek(
        @Path("location") location: String,
        @Query(QUERY_PARAM_UNIT_GROUP) unitGroup: String = UNIT_GROUP,
        @Query(QUERY_PARAM_INCLUDE) include: String = INCLUDE_WEEK,
        @Query(QUERY_PARAM_API_KEY) key: String = API_KEY,
        @Query(QUERY_PARAM_CONTENT_TYPE) contentType: String = CONTENT_TYPE
    ): WeatherInfoSevenDaysDto

    //Проноз погоды по часам
    @GET("{location}/today?")
    suspend fun getWeatherInfoNow(
        @Path("location") location: String,
        @Query(QUERY_PARAM_UNIT_GROUP) unitGroup: String = UNIT_GROUP,
        @Query(QUERY_PARAM_INCLUDE) include: String = INCLUDE_DAY,
        @Query(QUERY_PARAM_API_KEY) key: String = API_KEY,
        @Query(QUERY_PARAM_CONTENT_TYPE) contentType: String = CONTENT_TYPE
    ): WeatherInfoNowDto

    //Прогноз погоды сейчас
    @GET("{location}/today?")
    suspend fun getWeatherInfoHour(
        @Path("location") location: String,
        @Query(QUERY_PARAM_UNIT_GROUP) unitGroup: String = UNIT_GROUP,
        @Query(QUERY_PARAM_INCLUDE) include: String = INCLUDE_HOUR,
        @Query(QUERY_PARAM_API_KEY) key: String = API_KEY,
        @Query(QUERY_PARAM_CONTENT_TYPE) contentType: String = CONTENT_TYPE
    ): WeatherInfoHourDto


    companion object {
        private const val QUERY_PARAM_UNIT_GROUP = "unitGroup"
        private const val QUERY_PARAM_INCLUDE = "include"
        private const val QUERY_PARAM_API_KEY = "key"
        private const val QUERY_PARAM_CONTENT_TYPE = "contentType"


        private const val API_KEY = "PDPFUPG89PEE5GXQEKA6VWGDB"
        private const val UNIT_GROUP = "metric"
        private const val CONTENT_TYPE = "json"

        private const val INCLUDE_WEEK = "days"
        private const val INCLUDE_HOUR = "hours"
        private const val INCLUDE_DAY = "current"
    }
}
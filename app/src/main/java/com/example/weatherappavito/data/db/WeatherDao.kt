package com.example.weatherappavito.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherappavito.data.db.model.WeatherHourDb
import com.example.weatherappavito.data.db.model.WeatherNowDb
import com.example.weatherappavito.data.db.model.WeatherSevenDb

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_hour_day_table ORDER BY datetimeEpoch ASC ")
    fun getWeatherHourTable(): LiveData<List<WeatherHourDb>>

    @Query("SELECT * FROM weather_now_day_table ORDER BY datetimeEpoch ASC")
    fun getWeatherNowTable(): LiveData<WeatherNowDb>

    @Query("SELECT * FROM weather_days_week_table ORDER BY datetimeEpoch ASC")
    fun getWeatherWeekTable(): LiveData<List<WeatherSevenDb>>


    @Query("DELETE FROM weather_hour_day_table")
    suspend fun deleteWeatherHourTable()

    @Query("DELETE FROM weather_now_day_table")
    suspend fun deleteWeatherNowTable()

    @Query("DELETE FROM weather_days_week_table")
    suspend fun deleteWeatherDaysWeekTable()


    @Insert(entity = WeatherHourDb::class)
    suspend fun insertWeatherHourTable(weatherHourDbList : List<WeatherHourDb>?)

    @Insert(entity = WeatherSevenDb::class)
    suspend fun insertWeatherSevenDayTable(weatherSevenDbList : List<WeatherSevenDb>?)

    @Insert(entity = WeatherNowDb::class)
    suspend fun insertWeatherNowTable( weatherNowDbList : WeatherNowDb)


}
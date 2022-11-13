package com.example.weatherappavito.domain

import androidx.lifecycle.LiveData
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherNow
import com.example.weatherappavito.domain.entity.WeatherSevenDays
import com.example.weatherappavito.domain.useCase.LoadDataUseCase

interface WeatherRepository {

    fun getWeatherTodayUseCase():LiveData<List<WeatherHour>>

    fun getWeatherSevenDaysUseCase():LiveData<List<WeatherSevenDays>>

    fun getWeatherNowUseCase():LiveData<WeatherNow>

    suspend fun loadDataUseCase(location:String)
}
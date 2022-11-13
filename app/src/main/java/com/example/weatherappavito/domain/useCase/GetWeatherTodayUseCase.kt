package com.example.weatherappavito.domain.useCase

import androidx.lifecycle.LiveData
import com.example.weatherappavito.domain.WeatherRepository
import com.example.weatherappavito.domain.entity.WeatherHour

class GetWeatherTodayUseCase(private val repository: WeatherRepository) {

     operator fun invoke(): LiveData<List<WeatherHour>> {
        return repository.getWeatherTodayUseCase()
    }
}
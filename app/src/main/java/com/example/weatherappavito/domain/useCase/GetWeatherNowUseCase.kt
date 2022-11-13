package com.example.weatherappavito.domain.useCase

import androidx.lifecycle.LiveData
import com.example.weatherappavito.domain.WeatherRepository
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherNow

class GetWeatherNowUseCase(private val repository:WeatherRepository) {

     operator fun invoke(): LiveData<WeatherNow> {
         return repository.getWeatherNowUseCase()

    }
}
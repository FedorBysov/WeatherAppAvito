package com.example.weatherappavito.domain.useCase

import androidx.lifecycle.LiveData
import com.example.weatherappavito.domain.WeatherRepository
import com.example.weatherappavito.domain.entity.WeatherSevenDays

class GetWeatherSevenDaysUseCase(private val repository: WeatherRepository) {

     operator fun invoke():LiveData<List<WeatherSevenDays>> {
        return repository.getWeatherSevenDaysUseCase()
    }
}
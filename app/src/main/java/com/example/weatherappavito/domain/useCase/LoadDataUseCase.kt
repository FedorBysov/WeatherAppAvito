package com.example.weatherappavito.domain.useCase

import com.example.weatherappavito.domain.WeatherRepository

class LoadDataUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(location:String) = repository
}
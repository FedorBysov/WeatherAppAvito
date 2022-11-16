package com.example.weatherappavito.presenation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappavito.data.WeatherRepositoryIMPL
import com.example.weatherappavito.domain.useCase.GetWeatherNowUseCase
import com.example.weatherappavito.domain.useCase.GetWeatherSevenDaysUseCase
import com.example.weatherappavito.domain.useCase.GetWeatherTodayUseCase
import com.example.weatherappavito.domain.useCase.LoadDataUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application):AndroidViewModel(application) {

    private val repository = WeatherRepositoryIMPL(application)
    private val getWeatherNowUseCase = GetWeatherNowUseCase(repository)
    private val getWeatherSevenDaysUseCase = GetWeatherSevenDaysUseCase(repository)
    private val getWeatherTodayUseCase = GetWeatherTodayUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val weatherNowVM = getWeatherNowUseCase()
    val weatherWeekVM = getWeatherSevenDaysUseCase()
    val weatherHourVM = getWeatherTodayUseCase()

    init {
        viewModelScope.launch {
            loadDataUseCase("Saint-Petersburg")
        }
    }

}
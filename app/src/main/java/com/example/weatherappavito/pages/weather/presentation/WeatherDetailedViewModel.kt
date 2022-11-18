package com.example.weatherappavito.pages.weather.presentation

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherappavito.data.WeatherRepositoryIMPL
import com.example.weatherappavito.domain.useCase.GetWeatherNowUseCase
import com.example.weatherappavito.domain.useCase.GetWeatherSevenDaysUseCase
import com.example.weatherappavito.domain.useCase.GetWeatherTodayUseCase
import com.example.weatherappavito.domain.useCase.LoadDataUseCase
import com.google.android.gms.location.*
import kotlinx.coroutines.launch
import java.util.*

class WeatherDetailedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepositoryIMPL(application)
    private val getWeatherNowUseCase = GetWeatherNowUseCase(repository)
    private val getWeatherSevenDaysUseCase = GetWeatherSevenDaysUseCase(repository)
    private val getWeatherTodayUseCase = GetWeatherTodayUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    val weatherNowVM = getWeatherNowUseCase()
    val weatherWeekVM = getWeatherSevenDaysUseCase()
    val weatherHourVM = getWeatherTodayUseCase()

    fun loadData(string: String) {
        viewModelScope.launch() {
            loadDataUseCase(string)
        }
    }
}
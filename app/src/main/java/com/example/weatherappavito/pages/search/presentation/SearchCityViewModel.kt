package com.example.weatherappavito.pages.search.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappavito.data.WeatherRepositoryIMPL
import com.example.weatherappavito.domain.useCase.LoadDataUseCase
import kotlinx.coroutines.launch

class SearchCityViewModel(application: Application):AndroidViewModel(application) {


    private val repository = WeatherRepositoryIMPL(application)
    private val loadDataUseCase = LoadDataUseCase(repository)

    fun loadData(string: String) {
        viewModelScope.launch() {
            loadDataUseCase(string)
        }
    }
}
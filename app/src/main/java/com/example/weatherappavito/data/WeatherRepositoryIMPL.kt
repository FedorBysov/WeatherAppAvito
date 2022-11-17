package com.example.weatherappavito.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.weatherappavito.data.api.ApiFactory
import com.example.weatherappavito.data.db.DataBase
import com.example.weatherappavito.data.mapper.WeatherMapper
import com.example.weatherappavito.domain.WeatherRepository
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherNow
import com.example.weatherappavito.domain.entity.WeatherSevenDays
import kotlinx.coroutines.delay

class WeatherRepositoryIMPL(application: Application) : WeatherRepository {

    private val weatherDAO = DataBase.getInstance(application).weatherDao()
    private val apiService = ApiFactory.apiService
    private val mapper = WeatherMapper()


    override fun getWeatherTodayUseCase(): LiveData<List<WeatherHour>> {
        return Transformations.map(weatherDAO.getWeatherHourTable()) {
            it?.map {
                mapper.mapWeatherHourDbToEntity(it)
            }
        }
    }

    override fun getWeatherSevenDaysUseCase(): LiveData<List<WeatherSevenDays>> {
        return Transformations.map(weatherDAO.getWeatherWeekTable()) {
            it?.map {
                mapper.mapWeatherSevenDbToEntity(it)
            }
        }
    }

    override fun getWeatherNowUseCase(): LiveData<WeatherNow> =
        MediatorLiveData<WeatherNow>().apply {
            addSource(weatherDAO.getWeatherNowTable()) {
                if (it != null) {
                    value = mapper.mapWeatherNowDbToEntity(it)
                }
            }
        }

    override suspend fun loadDataUseCase(location: String) {
        while (true) {
            try {
                val weatherSevenDto = apiService.getWeatherInfoOneWeek(location)
                val weatherHourDto = apiService.getWeatherInfoHour(location)
                val weatherNowDto = apiService.getWeatherInfoNow(location)

                weatherDAO.deleteWeatherNowTable()
                weatherDAO.deleteWeatherDaysWeekTable()
                weatherDAO.deleteWeatherHourTable()


                val dbModelSevenList =
                    weatherSevenDto.days?.map { mapper.mapWeatherInfoSevenDaysDtoToDb(it) }


                val dbModelHourList =
                    weatherHourDto.days?.get(0)?.hours?.map { mapper.mapWeatherInfoHourDtoToDb(it) }


                val dbModelNow =
                    mapper.mapWeatherInfoNowDtoToDb(weatherNowDto, weatherNowDto.currentConditions,
                        weatherSevenDto.days?.get(0)?.tempmin ?: 0.0,
                            weatherSevenDto.days?.get(0)?.tempmax ?: 0.0)

                weatherDAO.insertWeatherHourTable(dbModelHourList)
                weatherDAO.insertWeatherNowTable(dbModelNow)
                weatherDAO.insertWeatherSevenDayTable(dbModelSevenList)
            } catch (e: Exception) {
            }
            delay(100000000)
        }
    }
}
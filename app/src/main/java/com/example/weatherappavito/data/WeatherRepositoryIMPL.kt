package com.example.weatherappavito.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.weatherappavito.data.api.ApiFactory
import com.example.weatherappavito.data.api.model.weatherWeekResponse.WeatherSevenDaysDto
import com.example.weatherappavito.data.db.DataBase
import com.example.weatherappavito.data.mapper.WeatherMapper
import com.example.weatherappavito.domain.WeatherRepository
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherNow
import com.example.weatherappavito.domain.entity.WeatherSevenDays
import kotlinx.coroutines.delay
import retrofit2.Response

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

        val weatherSevenDto = apiService.getWeatherInfoOneWeek(location)
        val weatherHourDto = apiService.getWeatherInfoHour(location)
        val weatherNowDto = apiService.getWeatherInfoNow(location)

        if (weatherHourDto.isSuccessful and weatherSevenDto.isSuccessful and weatherNowDto.isSuccessful ) {
            weatherDAO.deleteWeatherNowTable()
            weatherDAO.deleteWeatherDaysWeekTable()
            weatherDAO.deleteWeatherHourTable()


            val dbModelSevenList =
                weatherSevenDto.body()?.days?.map { mapper.mapWeatherInfoSevenDaysDtoToDb(it) }
            weatherDAO.insertWeatherSevenDayTable(dbModelSevenList)


            val dbModelHourList =
                weatherHourDto.body()?.days?.get(0)?.hours?.map {
                    mapper.mapWeatherInfoHourDtoToDb(
                        it
                    )
                }
            weatherDAO.insertWeatherHourTable(dbModelHourList)

            val dbModelNow =
                mapper.mapWeatherInfoNowDtoToDb(
                    weatherNowDto.body()!!, weatherNowDto.body()!!.currentConditions,
                    weatherSevenDto.body()?.days?.get(0)?.tempmin ?: 0.0,
                    weatherSevenDto.body()?.days?.get(0)?.tempmax ?: 0.0
                )
            weatherDAO.insertWeatherNowTable(dbModelNow)
        }
    }
}

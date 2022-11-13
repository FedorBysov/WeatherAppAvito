package com.example.weatherappavito.data.mapper

import androidx.room.PrimaryKey
import com.example.weatherappavito.data.api.model.weatherHourResponse.WeatherHourDto
import com.example.weatherappavito.data.api.model.weatherNowResponse.WeatherInfoNowDto
import com.example.weatherappavito.data.api.model.weatherNowResponse.WeatherNowDto
import com.example.weatherappavito.data.api.model.weatherWeekResponse.WeatherSevenDaysDto
import com.example.weatherappavito.data.db.model.WeatherHourDb
import com.example.weatherappavito.data.db.model.WeatherNowDb
import com.example.weatherappavito.data.db.model.WeatherSevenDb
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherNow
import com.example.weatherappavito.domain.entity.WeatherSevenDays

class WeatherMapper {


    fun mapWeatherInfoNowDtoToDb(
        infoNowDto: WeatherInfoNowDto,
        nowDto: WeatherNowDto
    ): WeatherNowDb {
        return WeatherNowDb(
            datetime = nowDto.datetime,
            datetimeEpoch = nowDto.datetimeEpoch,
            temp = nowDto.temp,
            feelslike = nowDto.feelslike,
            humidity = nowDto.humidity,
            dew = nowDto.dew,
            precip = nowDto.precip,
            precipprob = nowDto.precipprob,
            snow = nowDto.snow,
            snowdepth = nowDto.snowdepth,
            windgust = nowDto.windgust,
            windspeed = nowDto.windspeed,
            winddir = nowDto.winddir,
            pressure = nowDto.pressure,
            visibility = nowDto.visibility,
            cloudcover = nowDto.cloudcover,
            solarradiation = nowDto.solarradiation,
            uvindex = nowDto.uvindex,
            conditions = nowDto.conditions,
            icon = nowDto.icon,
            source = nowDto.source,
            sunrise = nowDto.sunrise,
            sunriseEpoch = nowDto.sunriseEpoch,
            sunset = nowDto.sunset,
            sunsetEpoch = nowDto.sunsetEpoch,

            address = infoNowDto.address
        )
    }

    fun mapWeatherInfoHourDtoToDb(weatherHourDto: WeatherHourDto): WeatherHourDb {
        return WeatherHourDb(
            datetime = weatherHourDto.datetime,
            datetimeEpoch = weatherHourDto.datetimeEpoch,
            temp = weatherHourDto.temp,
            humidity = weatherHourDto.humidity,
            icon = weatherHourDto.icon
        )

    }

    fun mapWeatherInfoSevenDaysDtoToDb(weatherSevenDaysDto: WeatherSevenDaysDto): WeatherSevenDb {
        return WeatherSevenDb(
            datetime = weatherSevenDaysDto.datetime,
            datetimeEpoch = weatherSevenDaysDto.datetimeEpoch,
            tempmax = weatherSevenDaysDto.tempmax,
            tempmin = weatherSevenDaysDto.tempmin,
            temp = weatherSevenDaysDto.temp,
            feelslike = weatherSevenDaysDto.feelslike,
            humidity = weatherSevenDaysDto.humidity,
            windspeed = weatherSevenDaysDto.windspeed
        )
    }


    fun mapWeatherHourDbToEntity(hourDb: WeatherHourDb): WeatherHour {
        return WeatherHour(
            datetime = hourDb.datetime,
            datetimeEpoch = hourDb.datetimeEpoch,
            temp = hourDb.temp,
            humidity = hourDb.humidity,
            icon = hourDb.icon
        )
    }

    fun mapWeatherNowDbToEntity(nowDb: WeatherNowDb): WeatherNow {
        return WeatherNow(
            datetime = nowDb.datetime,
            datetimeEpoch = nowDb.datetimeEpoch,
            temp = nowDb.temp,
            feelslike = nowDb.feelslike,
            humidity = nowDb.humidity,
            dew = nowDb.dew,
            precip = nowDb.precip,
            precipprob = nowDb.precipprob,
            snow = nowDb.snow,
            snowdepth = nowDb.snowdepth,
            windgust = nowDb.windgust,
            windspeed = nowDb.windspeed,
            winddir = nowDb.winddir,
            pressure = nowDb.pressure,
            visibility = nowDb.visibility,
            cloudcover = nowDb.cloudcover,
            solarradiation = nowDb.solarradiation,
            uvindex = nowDb.uvindex,
            conditions = nowDb.conditions,
            icon = nowDb.icon,
            source = nowDb.source,
            sunrise = nowDb.sunrise,
            sunriseEpoch = nowDb.sunriseEpoch,
            sunset = nowDb.sunset,
            sunsetEpoch = nowDb.sunsetEpoch,

            address = nowDb.address
        )
    }

    fun mapWeatherSevenDbToEntity(sevenDb: WeatherSevenDb): WeatherSevenDays {
        return WeatherSevenDays(
            datetime = sevenDb.datetime,
            datetimeEpoch = sevenDb.datetimeEpoch,
            tempmax = sevenDb.tempmax,
            tempmin = sevenDb.tempmin,
            temp = sevenDb.temp,
            feelslike = sevenDb.feelslike,
            humidity = sevenDb.humidity,
            windspeed = sevenDb.windspeed
        )
    }

}
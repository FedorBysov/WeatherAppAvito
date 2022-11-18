package com.example.weatherappavito.data.mapper

import com.example.weatherappavito.R
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
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class WeatherMapper {


    fun mapWeatherInfoNowDtoToDb(
        infoNowDto: WeatherInfoNowDto,
        nowDto: WeatherNowDto, tempMin: Double, tempMax: Double
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
            resolvedAddress = infoNowDto.resolvedAddress,

            address = infoNowDto.address,

            minTemp = tempMin,
            maxTemp = tempMax
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
            windspeed = weatherSevenDaysDto.windspeed,
            icon = weatherSevenDaysDto.icon
        )
    }


    fun mapWeatherHourDbToEntity(hourDb: WeatherHourDb): WeatherHour {
        return WeatherHour(
            datetime = convertTimeTempToSet(hourDb.datetimeEpoch?.toLong()),
            datetimeEpoch = hourDb.datetimeEpoch,
            temp = hourDb.temp,
            humidity = hourDb.humidity,
            icon = selectedImage(hourDb.icon)
        )
    }

    fun mapWeatherNowDbToEntity(nowDb: WeatherNowDb): WeatherNow {
        return WeatherNow(
            datetime = convertDayToSet(nowDb.datetimeEpoch?.toLong()) + ", " + convertMonthToSet(
                nowDb.datetimeEpoch?.toLong()
            ),
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
            icon = selectedImage(nowDb.icon),
            source = nowDb.source,
            sunrise = nowDb.sunrise,
            sunriseEpoch = nowDb.sunriseEpoch,
            sunset = nowDb.sunset,
            sunsetEpoch = nowDb.sunsetEpoch,

            address = nowDb.address,

            maxTemp = nowDb.maxTemp,
            minTemp = nowDb.minTemp,
            resolvedAddress = nowDb.resolvedAddress
        )
    }

    fun mapWeatherSevenDbToEntity(sevenDb: WeatherSevenDb): WeatherSevenDays {
        return WeatherSevenDays(
            datetime = convertWeekToSet(sevenDb.datetimeEpoch?.toLong()),
            datetimeEpoch = sevenDb.datetimeEpoch,
            tempmax = sevenDb.tempmax,
            tempmin = sevenDb.tempmin,
            temp = sevenDb.temp,
            feelslike = sevenDb.feelslike,
            humidity = sevenDb.humidity,
            windspeed = sevenDb.windspeed,
            icon = selectedImage(sevenDb.icon)
        )
    }


    private fun convertTimeTempToSet(timeStemp: Long?): String {
        if (timeStemp == null) return ""
        val stemp = Timestamp(timeStemp * 1000)
        val day = Date(stemp.time)
        val pattern = "HH:mm"
        // Время по гринвичу
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(day)
    }

    private fun convertWeekToSet(timeStemp: Long?): String {
        if (timeStemp == null) return ""
        val stemp = (timeStemp * 1000)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = stemp
        return calendar.getDisplayName(
            Calendar.DAY_OF_WEEK,
            Calendar.LONG,
            Locale.ENGLISH
        ) as String
    }

    private fun convertDayToSet(timeStemp: Long?): String {
        if (timeStemp == null) return ""
        val stemp = (timeStemp * 1000)
        return SimpleDateFormat("dd").format(Date(stemp)).toString()
    }

    private fun convertMonthToSet(timeStemp: Long?): String {
        if (timeStemp == null) return ""
        val stemp = (timeStemp * 1000)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = stemp
        return calendar.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG,
            Locale.ENGLISH
        ) as String
    }


    fun selectedImage(image: String?): Int {
        val resource: Int =
            when (image) {
                "clear-day" -> (R.drawable.ic_clear_day)
                "clear-night" -> (R.drawable.ic_clear_night)
                "cloudy" -> (R.drawable.ic_cloudy)
                "sleet" -> (R.drawable.ic_sleet)
                "snow" -> (R.drawable.ic_snow)
                "snow-showers-day" -> (R.drawable.ic_snow_showers_day)
                "snow-showers-night" -> (R.drawable.ic_showers_night)
                "thunder" -> (R.drawable.ic_thunder)
                "thunder-rain" -> (R.drawable.ic_thunder_rain)
                "thunder-showers-day" -> (R.drawable.ic_thunder_showers_day)
                "thunder-showers-night" -> (R.drawable.ic_thunder_showers_night)
                "rain" -> (R.drawable.ic_rain)
                "rain-snow" -> (R.drawable.ic_rain_snow)
                "rain-snow-showers-day" -> (R.drawable.ic_rain_snow_showers_day)
                "rain-snow-showers-night" -> (R.drawable.ic_thunder_showers_night)
                "showers-day" -> (R.drawable.ic_showers_day)
                "showers-night" -> (R.drawable.ic_showers_night)
                "fog" -> (R.drawable.ic_fog)
                "wind" -> (R.drawable.ic_wind)
                "partly-cloudy-day" -> (R.drawable.ic_partly_cloudy_day)
                "partly-cloudy-night" -> (R.drawable.ic_partly_cloudy_night)
                "hail" -> (R.drawable.ic_hail)

                else -> (R.drawable.ic_launcher_background)
            }
        return resource.toInt()
    }
}


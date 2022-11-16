package com.example.weatherappavito.presenation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weather_detailed_container, WeatherDetailedFragment.newInstance())
            .commit()

//        val a = CoroutineScope(Dispatchers.Main)
//        val repository = WeatherRepositoryIMPL(application)
//        val getWeatherNowUseCase = GetWeatherNowUseCase(repository)



//        a.launch {
////            val b =ApiFactory.apiService.getWeatherInfoNow(location = "Moscow")
////            Log.d("123Z", "${b}")
//            repository.loadDataUseCase("Moscow")
//        }

//        val live = getWeatherNowUseCase()
//        live.observe(this) {
//            if (it != null) {
//                Log.d("321Z", "$it")
//            }
//        }






    }
}
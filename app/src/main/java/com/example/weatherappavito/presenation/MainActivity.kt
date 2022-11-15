package com.example.weatherappavito.presenation

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappavito.data.WeatherRepositoryIMPL
import com.example.weatherappavito.data.api.ApiFactory
import com.example.weatherappavito.databinding.ActivityMainBinding
import com.example.weatherappavito.domain.useCase.GetWeatherNowUseCase
import com.example.weatherappavito.domain.useCase.LoadDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val a = CoroutineScope(Dispatchers.Main)
        val repository = WeatherRepositoryIMPL(application)
        val getWeatherNowUseCase = GetWeatherNowUseCase(repository)



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
package com.example.weatherappavito.presenation

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
import androidx.fragment.app.Fragment
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

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepositoryIMPL(application)
    private val getWeatherNowUseCase = GetWeatherNowUseCase(repository)
    private val getWeatherSevenDaysUseCase = GetWeatherSevenDaysUseCase(repository)
    private val getWeatherTodayUseCase = GetWeatherTodayUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: android.location.LocationRequest


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorLoad = MutableLiveData<Boolean>()
    val errorLoad: LiveData<Boolean>
        get() = _errorInputName


    val weatherNowVM = getWeatherNowUseCase()
    val weatherWeekVM = getWeatherSevenDaysUseCase()
    val weatherHourVM = getWeatherTodayUseCase()
//    init {

    fun loadData(string: String) {
        viewModelScope.launch() {
                loadDataUseCase(string)
        }
    }
//    }


    suspend fun location(context: Context, activity: Activity,int: Int) {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        requestPermission(context,activity, int)
        getLastLocation(context,activity, int)
    }


    suspend fun getLastLocation(context: Context, activity: Activity, int: Int) {
        if (сheckPermission(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(context)
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        viewModelScope.launch {
                            loadData(
                                newLocationData(
                                    context,
                                    activity
                                )
                            )
                        }
                    } else {
                        viewModelScope.launch {
                            loadData(
                                getCityName(
                                    location.latitude,
                                    location.longitude,
                                    context
                                )
                            )
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            viewModelScope.launch {
                requestPermission(context, activity, int)
            }
        }
    }


    //1
    suspend fun сheckPermission(context: Context): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //2

    suspend fun newLocationData(context: Context, activity: Activity): String {


        val locationRequest = LocationRequest()
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)
        val a = if (
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest, test(context), Looper.myLooper()
            ).toString()
        } else {
            ""
        }
        return a
    }

    private fun test(context: Context): LocationCallback {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val lastLocation: Location = locationResult.lastLocation!!
//                Log.d(
//                    "Debug",
//                    "${
//                        viewModelScope.launch {
//                            getCityName(
//                                lastLocation.latitude,
//                                lastLocation.longitude,
//                                context
//                            )
//                        }
//                    }"
//                )
            }
        }
        return locationCallback
    }

    //3
    suspend fun getCityName(lat: Double, long: Double, context: Context): String {
        var cityName: String = ""
        val geoCoder = Geocoder(context, Locale.getDefault())
        val Adress = geoCoder.getFromLocation(lat, long, 3)

        cityName = Adress?.get(0)?.locality ?: "Moscow"
        Log.d("Debug:", cityName)
        return cityName
    }


    //4

    suspend fun requestPermission(context: Context, activity: Activity, int: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            int
        )
    }


    fun validateName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    fun resetInputName() {
        _errorInputName.value = false
    }

}
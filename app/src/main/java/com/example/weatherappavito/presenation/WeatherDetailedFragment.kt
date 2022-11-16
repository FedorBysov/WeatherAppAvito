package com.example.weatherappavito.presenation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weatherappavito.databinding.FragmentWeatherDetailedBinding
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherHour
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherWeek
import com.google.android.gms.location.*
import kotlinx.coroutines.launch
import java.util.*


class WeatherDetailedFragment : Fragment() {

    private var _binding: FragmentWeatherDetailedBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: WeatherViewModel

    //
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherDetailedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        val adapterHour = AdapterWeatherHour()
        val adapterWeek = AdapterWeatherWeek()

//        fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(requireContext())
//
//        requestPermission(requireContext())
//        getLastLocation(requireContext())
        lifecycleScope.launch {
            location(requireContext())
        }

        binding.rvWeatherHour.adapter = adapterHour
        binding.rvWeatherWeek.adapter = adapterWeek
        binding.rvWeatherHour.itemAnimator = null
        binding.rvWeatherWeek.itemAnimator = null

        viewModel.weatherHourVM.observe(viewLifecycleOwner) {
            adapterHour.submitList(it)
        }
        viewModel.weatherWeekVM.observe(viewLifecycleOwner) {
            adapterWeek.submitList(it)
        }

        viewModel.weatherNowVM.observe(viewLifecycleOwner) {
//            binding.tvMaxTemp.text = it.tempMax?.toInt().toString()
//            binding.tvMinTemp.text = it.tempMin?.toInt().toString()
            binding.tvLocationNow.text = it.address
            binding.ivWeatherNow.setImageResource(it.icon)
            binding.tvTempNow.text = String.format("%S°", it.temp?.toInt().toString())
//            binding.tvDataToday.text = it.date
            binding.tvPrecip.text = String.format("%s %%", it.precip?.toInt().toString())
            binding.tvHumidity.text = String.format("%S %%", it.humidity?.toInt().toString())
            binding.tvSpeedWind.text = String.format("%s Km/h", it.windspeed?.toInt().toString())
        }



        binding.fbLocationDetermination.setOnClickListener {
            lifecycleScope.launch {
                location(requireContext())
            }
        }

    }

    suspend fun location(context: Context) {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        requestPermission(context)
        getLastLocation(context)
    }

    suspend fun getLastLocation(context: Context) {
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
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        lifecycleScope.launch {
                            NewLocationData(context)
                        }
                    } else {
                        Log.d(
                            "Debug",
                            "${
                                lifecycleScope.launch {
                                    getCityName(
                                        location.latitude,
                                        location.longitude,
                                        context
                                    )
                                }
                            }"
                        )

                    }
                }
            } else {
                Toast.makeText(context, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            requestPermission(context)
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

    suspend fun NewLocationData(context: Context) {
        var locationRequest = LocationRequest()
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
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
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
            )
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation!!
            Log.d(
                "Debug",
                "${
                    lifecycleScope.launch {
                        getCityName(
                            lastLocation.latitude,
                            lastLocation.longitude,
                            requireContext()
                        )
                    }
                }"
            )
        }
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

    suspend fun requestPermission(context: Context) {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_CODE
        )
    }


    companion object {

        private const val REQUEST_CODE = 101

        @JvmStatic
        fun newInstance() = WeatherDetailedFragment()
    }
}
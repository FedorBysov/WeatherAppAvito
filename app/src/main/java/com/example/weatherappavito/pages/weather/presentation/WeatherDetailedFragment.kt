package com.example.weatherappavito.pages.weather.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentWeatherDetailedBinding
import com.example.weatherappavito.pages.weather.adapter.AdapterWeatherHour
import com.example.weatherappavito.pages.weather.adapter.AdapterWeatherWeek
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.item_search_city.*
import kotlinx.coroutines.launch
import java.util.*


class WeatherDetailedFragment : Fragment() {

    private var _binding: FragmentWeatherDetailedBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: WeatherDetailedViewModel

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

        viewModel = ViewModelProvider(this)[WeatherDetailedViewModel::class.java]

        initAdapter()

        if (isOnline(requireContext())) {
            lifecycleScope.launch {
                location(requireContext())
            }
        }

        viewModel.weatherNowVM.observe(viewLifecycleOwner) {
            binding.textViewMaxTemp.text = String.format("Max: %S°", it.maxTemp?.toInt().toString())
            binding.textViewMinTemp.text = String.format("Min: %S°", it.minTemp?.toInt().toString())
            binding.textViewLocationNow.text = it.resolvedAddress
            binding.imageViewWeatherNow.setImageResource(it.icon)
            binding.textViewTempNow.text = String.format("%S°", it.temp?.toInt().toString())
            binding.textViewDataToday.text = it.datetime
            binding.textViewPrecip.text = String.format("%s %%", it.precip?.toInt().toString())
            binding.textViewHumidity.text = String.format("%S %%", it.humidity?.toInt().toString())
            binding.textViewSpeedWind.text =
                String.format("%s Km/h", it.windspeed?.toInt().toString())
        }

        binding.floatingButtonSearchCity.setOnClickListener {
            showBottomSheetDialog()
        }

        binding.floatingButtonLocationDetermination.setOnClickListener {
            if (isOnline(requireContext())) {
                lifecycleScope.launch {
                    location(requireContext())
                }
            }
        }

    }

    private fun initAdapter(){
        val adapterHour = AdapterWeatherHour()
        val adapterWeek = AdapterWeatherWeek()
        binding.recyclerViewWeatherHour.adapter = adapterHour
        binding.recyclerViewWeatherWeek.adapter = adapterWeek
        binding.recyclerViewWeatherHour.itemAnimator = null
        binding.recyclerViewWeatherWeek.itemAnimator = null
        viewModel.weatherHourVM.observe(viewLifecycleOwner) {
            adapterHour.submitList(it)
        }
        viewModel.weatherWeekVM.observe(viewLifecycleOwner) {
            adapterWeek.submitList(it)
        }
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.item_search_city)
        dialog.btnSearch.setOnClickListener {
            if (isOnline(requireContext())) {
                viewModel.loadData(dialog.editTextSearch.text.toString())
                dialog.dismiss()
            }

        }
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog.show()
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
                            viewModel.loadData(newLocationData(context))
                        }
                    } else {
                        Log.d(
                            "Debug",
                            "${
                                lifecycleScope.launch {
                                    viewModel.loadData(
                                        getCityName(
                                            location.latitude,
                                            location.longitude,
                                            context
                                        )
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


    suspend fun newLocationData(context: Context): String {
        val locationRequest = com.google.android.gms.location.LocationRequest()
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
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
            }
        }
        return locationCallback
    }

    //3
    suspend fun getCityName(lat: Double, long: Double, context: Context): String {
        var cityName: String = ""
        val geoCoder = Geocoder(context, Locale.getDefault())
        val Adress = geoCoder.getFromLocation(lat, long, 3)

        cityName = Adress?.get(0)?.locality ?: ""
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

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    companion object {

        const val NAME = "info"

        private const val REQUEST_CODE = 101

        @JvmStatic
        fun newInstance() = WeatherDetailedFragment()
    }
}
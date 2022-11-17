package com.example.weatherappavito.pages.weather.presentation

import android.location.LocationRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentWeatherDetailedBinding
import com.example.weatherappavito.pages.search.presentation.SearchCityFragment
import com.example.weatherappavito.pages.weather.adapter.AdapterWeatherHour
import com.example.weatherappavito.pages.weather.adapter.AdapterWeatherWeek
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch


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
        val adapterHour = AdapterWeatherHour()
        val adapterWeek = AdapterWeatherWeek()

        lifecycleScope.launch {
            viewModel.location(requireContext(), requireActivity(), REQUEST_CODE)
        }


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

        viewModel.weatherNowVM.observe(viewLifecycleOwner) {
            binding.textViewMaxTemp.text = String.format("Max: %S°", it.maxTemp?.toInt().toString())
            binding.textViewMinTemp.text = String.format("Min: %S°", it.minTemp?.toInt().toString())
            binding.textViewLocationNow.text = it.address
            binding.imageViewWeatherNow.setImageResource(it.icon)
            binding.textViewTempNow.text = String.format("%S°", it.temp?.toInt().toString())
            binding.textViewDataToday.text = it.datetime
            binding.textViewPrecip.text = String.format("%s %%", it.precip?.toInt().toString())
            binding.textViewHumidity.text = String.format("%S %%", it.humidity?.toInt().toString())
            binding.textViewSpeedWind.text =
                String.format("%s Km/h", it.windspeed?.toInt().toString())
        }


        binding.floatingButtonSearchCity.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(NAME)
                .add(R.id.weather_detailed_container, SearchCityFragment())
                .commit()
        }

        binding.floatingButtonLocationDetermination.setOnClickListener {
            lifecycleScope.launch {
                viewModel.location(requireContext(), requireActivity(), REQUEST_CODE)
            }
        }

    }


    companion object {

        const val NAME = "info"

        private const val REQUEST_CODE = 101

        @JvmStatic
        fun newInstance() = WeatherDetailedFragment()
    }
}
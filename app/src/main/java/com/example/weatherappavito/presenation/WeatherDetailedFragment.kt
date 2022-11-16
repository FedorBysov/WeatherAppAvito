package com.example.weatherappavito.presenation

import android.location.LocationRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentWeatherDetailedBinding
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherHour
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherWeek
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch


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

        lifecycleScope.launch {
            viewModel.location(requireContext(),requireActivity(), REQUEST_CODE)
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


        binding.fbSearchCity.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(NAME)
                .add(R.id.weather_detailed_container, SearchCityFragment())
                .commit()
        }

        binding.fbLocationDetermination.setOnClickListener {
            lifecycleScope.launch {
                viewModel.location(requireContext(),requireActivity(), REQUEST_CODE)
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
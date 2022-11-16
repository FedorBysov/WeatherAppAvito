package com.example.weatherappavito.presenation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentWeatherDetailedBinding
import com.example.weatherappavito.domain.entity.WeatherNow
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherHour
import com.example.weatherappavito.presenation.adaapters.AdapterWeatherWeek
import kotlinx.android.synthetic.main.fragment_weather_detailed.view.*


class WeatherDetailedFragment : Fragment() {

    private var _binding: FragmentWeatherDetailedBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: WeatherViewModel

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

    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherDetailedFragment()
    }

}
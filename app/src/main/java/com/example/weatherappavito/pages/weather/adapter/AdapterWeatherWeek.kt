package com.example.weatherappavito.pages.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherappavito.databinding.ItemWeatherWeekBinding
import com.example.weatherappavito.domain.entity.WeatherSevenDays

class AdapterWeatherWeek : ListAdapter<WeatherSevenDays, WeekViewHolder>(WeekDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val binding =
            ItemWeatherWeekBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvTemperatureWeekDayItem.text = datetime
                tvTemperatureMaxWeekItem.text = String.format("%S°",tempmax?.toInt().toString())
                tvTemperatureMinWeekItem.text = String.format("%S°",tempmax?.toInt().toString())
                ivTemperatureWeekItem.setImageResource(icon)
            }
        }
    }
}
package com.example.weatherappavito.presenation.adaapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.ItemWeatherHourBinding
import com.example.weatherappavito.domain.entity.WeatherHour

class AdapterWeatherHour : ListAdapter<WeatherHour, HourViewHolder>(HourDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val binding =
            ItemWeatherHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvTemperatureHourItem.text = String.format("%s°", temp?.toInt())
                tvTimeHourItem.text = datetime
                ivPictureHourItem.setImageResource(icon)
            }
        }
    }
}
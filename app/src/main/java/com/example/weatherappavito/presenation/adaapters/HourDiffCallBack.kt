package com.example.weatherappavito.presenation.adaapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherappavito.domain.entity.WeatherHour

class HourDiffCallBack: DiffUtil.ItemCallback<WeatherHour>() {
    override fun areItemsTheSame(oldItem: WeatherHour, newItem: WeatherHour): Boolean {
        return oldItem.datetimeEpoch == newItem.datetimeEpoch
    }

    override fun areContentsTheSame(oldItem: WeatherHour, newItem: WeatherHour): Boolean {
        return oldItem == newItem
    }
}
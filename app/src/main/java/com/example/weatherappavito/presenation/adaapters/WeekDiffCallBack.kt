package com.example.weatherappavito.presenation.adaapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherappavito.domain.entity.WeatherHour
import com.example.weatherappavito.domain.entity.WeatherSevenDays

class WeekDiffCallBack:DiffUtil.ItemCallback<WeatherSevenDays>() {

    override fun areItemsTheSame(oldItem: WeatherSevenDays, newItem: WeatherSevenDays): Boolean {
        return oldItem.datetimeEpoch == newItem.datetimeEpoch
    }

    override fun areContentsTheSame(oldItem: WeatherSevenDays, newItem: WeatherSevenDays): Boolean {
        return oldItem == newItem
    }
}
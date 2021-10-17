package com.aa.weatherforecast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aa.weatherforecast.api.entity.Forecast
import com.aa.weatherforecast.repository.WeatherRepository
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {
    // if internter connnection is taken into consideration .
    // i inject a internet  connectivty class into consideration
    private val repository : WeatherRepository = WeatherRepository()

    private val mShowProgress = MutableLiveData<Boolean>()
    val showProgres : LiveData<Boolean> = mShowProgress

    private val mShowDailyForcast = MutableLiveData<Forecast>()
    val showDailyForecast : LiveData<Forecast> = mShowDailyForcast

    fun getForcastDetailsWithLatAndLng(lat :String ,lng: String) {
        // Not taking any internet connection state into consideration
        viewModelScope.launch {
            mShowProgress.value = true
            // I would have implemnted a sealed class for response call back
            // Success - > Forcast data
            // Failure -> Error (which is specify)
            val forcast = repository.getForecastByCity(lat,lng)
            mShowProgress.value = false
            mShowDailyForcast.value = forcast
        }
    }
}
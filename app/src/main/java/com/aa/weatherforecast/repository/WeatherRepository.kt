package com.aa.weatherforecast.repository

import com.aa.weatherforecast.api.DailyForecastApi
import com.aa.weatherforecast.api.entity.Forecast
import com.aa.weatherforecast.network.NetworkService
// DI with network service in constructor injected
class WeatherRepository {
    private val network = NetworkService()

    suspend fun getForecastByCity(lat:String, lon:String) : Forecast{
        return network.getRetrofitInstance().create(DailyForecastApi::class.java).forecastByCityCoroutine(lat, lon)
    }
}
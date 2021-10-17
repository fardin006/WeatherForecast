package com.aa.weatherforecast.api

import com.aa.weatherforecast.api.entity.Forecast
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chad Doepken
 * 1/5/21
 */
interface DailyForecastApi {

    @GET("onecall?appid=3266efaedce0bdae97f925cfc7f971b2&exclude=current,minutely,hourly,alerts&units=imperial")
    fun forecastByCity(@Query("lat") latitude: String,
                       @Query("lon") longitude: String): Call<Forecast>

    @GET("onecall?appid=3266efaedce0bdae97f925cfc7f971b2&exclude=current,minutely,hourly,alerts&units=imperial")
    fun forecastByCityObservable(@Query("lat") latitude: String,
                                 @Query("lon") longitude: String): Observable<Forecast>

    @GET("onecall?appid=3266efaedce0bdae97f925cfc7f971b2&exclude=current,minutely,hourly,alerts&units=imperial")
    suspend fun forecastByCityCoroutine(@Query("lat") latitude: String,
                                 @Query("lon") longitude: String): Forecast



}
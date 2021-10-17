package com.aa.weatherforecast.api.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "dt")
    val dt: Long,
    @Json(name = "sunrise")
    val sunrise: Long,
    @Json(name = "sunset")
    val sunset: Long,
    @Json(name = "temp")
    val temp: Temp,
    @Json(name = "feels_like")
    val feelsLike: FeelsLike,
    @Json(name = "pressure")
    val pressure: Double,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "speed")
    val speed: Double?,
    @Json(name = "deg")
    val deg: Int?,
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "pop")
    val pop: Double
)
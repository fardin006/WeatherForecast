package com.aa.weatherforecast.api.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "lat")
    val lat: Double
)
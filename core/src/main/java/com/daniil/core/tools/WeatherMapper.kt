package com.daniil.core.tools

import com.daniil.core.domain.entity.Condition
import com.daniil.core.domain.entity.CurrentWeather
import com.daniil.core.domain.entity.Location
import com.daniil.core.data.remote.entity.CurrentHourDto
import com.daniil.core.data.remote.entity.LocationDto
import com.daniil.core.data.remote.responce.WeatherApiResponse
import com.daniil.core.domain.entity.Weather

//WeatherApiResponse(
// location=LocationDto(city=Jaroslavl, numberPhone=Yaroslavl', country=Russia, latitude=57.62, lontitude=39.87),
//
// current=CurrentHourDto(temperatureCelsius=-0.7
//
// condition=ConditionDto(temperatureIcon=//cdn.weatherapi.com/weather/64x64/day/122.png), windSpeed=16.9))
fun LocationDto.toLocation() = Location(
    city = city,
    region = region,
    country = country,
    latitude = latitude,
    longitude = longitude
)

fun CurrentHourDto.toCurrentWeather() = CurrentWeather(
    temperature = temperatureCelsius,
    condition = Condition(condition.temperatureIcon),
    windSpeed = windSpeed
)

fun Location.toWeather(currentWeather:CurrentWeather) = Weather(
    location = this,
    currentWeather = currentWeather
 )

fun CurrentWeather.toWeather(location: Location) = Weather(
    location = location,
    currentWeather = this
)

fun WeatherApiResponse.toWeather() = Weather(
    location = location.toLocation(),
    currentWeather = current.toCurrentWeather()
)



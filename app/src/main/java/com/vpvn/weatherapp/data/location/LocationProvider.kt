package com.vpvn.weatherapp.data.location

import com.vpvn.weatherapp.domain.model.LocationCoordinates

interface LocationProvider {
    suspend fun getLastLocation(): LocationCoordinates?
    suspend fun getCurrentLocation(): LocationCoordinates?
}
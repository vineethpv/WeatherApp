package com.vpvn.weatherapp.ui.fake

import com.vpvn.weatherapp.core.testing.TestData
import com.vpvn.weatherapp.data.location.LocationProvider
import com.vpvn.weatherapp.domain.model.LocationCoordinates

class FakeLocationProvider : LocationProvider {

    var shouldThrowError = false

    override suspend fun getLastLocation(): LocationCoordinates {
        return TestData.location
    }

    override suspend fun getCurrentLocation(): LocationCoordinates {
        if (shouldThrowError) throw Exception("Location error")
        return TestData.location
    }
}
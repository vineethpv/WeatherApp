package com.vpvn.weatherapp.data.remote.repository

import com.vpvn.weatherapp.core.testing.TestData
import com.vpvn.weatherapp.data.fake.FakeWeatherApi
import com.vpvn.weatherapp.domain.model.LocationCoordinates
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    private lateinit var api: FakeWeatherApi
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setup() {
        api = FakeWeatherApi()
        repository = WeatherRepositoryImpl(api)
    }

    @Test
    fun `returns grouped and mapped forecast`() = runTest {
        val result = repository.getFiveDayForecast(TestData.location)

        assert(result.isNotEmpty())
        assert(result.size <= 5)
    }

    @Test(expected = Exception::class)
    fun `throws when api fails`() = runTest {
        api.shouldThrowError = true

        repository.getFiveDayForecast(TestData.location)
    }
}
package com.vpvn.weatherapp.domain.usecase

import com.vpvn.weatherapp.data.fake.FakeWeatherRepository
import com.vpvn.weatherapp.domain.model.LocationCoordinates
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetFiveDayForecastUseCaseTest {

    private lateinit var fakeRepository: FakeWeatherRepository
    private lateinit var useCase: GetFiveDayForecastUseCase

    @Before
    fun setup() {
        fakeRepository = FakeWeatherRepository()
        useCase = GetFiveDayForecastUseCase(fakeRepository)
    }

    @Test
    fun `invoke returns data from repository`() = runTest {
        val result = useCase(LocationCoordinates(1.0, 2.0))

        assertTrue(result.isNotEmpty())
        assert(result == fakeRepository.fakeData)
    }

    @Test(expected = Exception::class)
    fun `invoke throws when repository fails`() = runTest {
        fakeRepository.shouldThrowError = true
        useCase(LocationCoordinates(1.0, 2.0))
    }
}
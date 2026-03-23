package com.vpvn.weatherapp.ui.forecast

import app.cash.turbine.test
import com.vpvn.weatherapp.core.testing.MainDispatcherRule
import com.vpvn.weatherapp.data.fake.FakeWeatherRepository
import com.vpvn.weatherapp.domain.usecase.GetFiveDayForecastUseCase
import com.vpvn.weatherapp.ui.fake.FakeLocationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var fakeRepository: FakeWeatherRepository
    private lateinit var fakeLocationProvider: FakeLocationProvider
    private lateinit var useCase: GetFiveDayForecastUseCase
    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        fakeRepository = FakeWeatherRepository()
        fakeLocationProvider = FakeLocationProvider()
        useCase = GetFiveDayForecastUseCase(fakeRepository)
        viewModel = WeatherViewModel(useCase, fakeLocationProvider)
    }

    @Test
    fun `LoadWeather emits Loading then Success`() = runTest {
        viewModel.uiState.test {

            // Initial state
            assert(awaitItem() is WeatherUiState.Loading)

            viewModel.handleIntent(WeatherIntent.LoadWeather)

            val result = awaitItem()
            assert(result is WeatherUiState.Success)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `LoadWeather emits Error when repository fails`() = runTest {
        fakeRepository.shouldThrowError = true

        viewModel.uiState.test {
            assert(awaitItem() is WeatherUiState.Loading)

            viewModel.handleIntent(WeatherIntent.LoadWeather)

            val result = awaitItem()
            assert(result is WeatherUiState.Error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `PermissionDenied emits Error`() = runTest {
        viewModel.handleIntent(WeatherIntent.PermissionDenied)

        assert(viewModel.uiState.value is WeatherUiState.Error)
    }
}
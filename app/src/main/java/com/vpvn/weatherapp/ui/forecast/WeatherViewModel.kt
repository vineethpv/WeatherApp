package com.vpvn.weatherapp.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpvn.weatherapp.data.location.LocationProvider
import com.vpvn.weatherapp.domain.usecase.GetFiveDayForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getFiveDayForecastUseCase: GetFiveDayForecastUseCase,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun handleIntent(intent: WeatherIntent) {
        when (intent) {
            WeatherIntent.LoadWeather,
            WeatherIntent.PermissionGranted -> loadWeather()
            WeatherIntent.PermissionDenied -> _uiState.value = WeatherUiState.Error("Permission Denied")
            WeatherIntent.LocationDisabled -> _uiState.value = WeatherUiState.Error("Location Disabled")
        }
    }

    private fun loadWeather() {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val locationCoordinates = locationProvider.getCurrentLocation()
                val forecast = getFiveDayForecastUseCase(locationCoordinates)
                _uiState.value = WeatherUiState.Success(forecast)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Error")
            }
        }
    }
}
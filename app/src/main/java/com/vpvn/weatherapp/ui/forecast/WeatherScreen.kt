package com.vpvn.weatherapp.ui.forecast

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cc.dvtweather.util.PermissionUtil
import com.vpvn.weatherapp.R
import com.vpvn.weatherapp.helper.LocationHelper.checkLocationSettings

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context as Activity

    val resolutionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.handleIntent(WeatherIntent.LoadWeather)
        } else {
            viewModel.handleIntent(WeatherIntent.LocationDisabled)
        }
    }

    var retry by remember { mutableStateOf(false) }

    PermissionGate(
        retryTrigger = retry,
        onGranted = {
            checkLocationSettings(
                activity = activity,
                onLocationEnabled = {
                    viewModel.handleIntent(WeatherIntent.LoadWeather)
                },
                onResolutionRequired = { intentSenderRequest ->
                    resolutionLauncher.launch(intentSenderRequest)
                }
            )

        },
        onDenied = { viewModel.handleIntent(WeatherIntent.PermissionDenied) }
    ) {
        when (state) {
            WeatherUiState.Loading -> {
                CenteredContent {
                    CircularProgressIndicator()
                }
            }

            is WeatherUiState.Success -> {
                val data = (state as WeatherUiState.Success).forecast
                WeatherBackground(
                    weatherType = data.first().weatherType,
                    content = {
                        ForecastList(
                            forecasts = data,
                            contentPaddingValues = paddingValues
                        )
                    })
            }

            is WeatherUiState.Error -> {
                val error = (state as WeatherUiState.Error).error
                CenteredContent {
                    ErrorContent(error, onRetry = {
                        when (error) {
                            WeatherError.PermissionDenied,
                            WeatherError.LocationDisabled -> {
                                retry = !retry
                            }
                            WeatherError.LocationUnavailable -> viewModel.handleIntent(WeatherIntent.LoadWeather)
                            is WeatherError.UnKnown -> viewModel.handleIntent(WeatherIntent.LoadWeather)
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun ErrorContent(
    error: WeatherError,
    onRetry: () -> Unit
) {
    val message = when (error) {
        WeatherError.LocationDisabled -> stringResource(R.string.location_is_turned_off)
        WeatherError.LocationUnavailable -> stringResource(R.string.unable_to_get_location)
        WeatherError.PermissionDenied -> stringResource(R.string.location_permission_denied)
        is WeatherError.UnKnown -> error.message ?: stringResource(R.string.something_went_wrong)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = message,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun CenteredContent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun PermissionGate(
    retryTrigger: Boolean,
    onGranted: () -> Unit,
    onDenied: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                || permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (permissionGranted) onGranted() else onDenied()
    }

    // Check on first composition(Unit) and retryTrigger for retries; launch dialog if needed
    LaunchedEffect(Unit, retryTrigger) {
        permissionGranted = PermissionUtil.hasLocationPermission(context)
        if (permissionGranted) {
            onGranted()
        } else {
            launcher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    content()
}

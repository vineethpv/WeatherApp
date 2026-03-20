package com.vpvn.weatherapp.ui.forecast

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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

    PermissionGate(
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
            is WeatherUiState.Loading -> {
                CenteredContent {
                    CircularProgressIndicator()
                }
            }

            is WeatherUiState.Success -> {
                val data = (state as WeatherUiState.Success).forecast
                ForecastList(
                    forecasts = data,
                    contentPaddingValues = paddingValues
                )
            }

            is WeatherUiState.Error -> {
                CenteredContent {
                    Text(stringResource(R.string.something_went_wrong) + (state as WeatherUiState.Error).message)
                }
            }
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
    onGranted: () -> Unit,
    onDenied: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    var permissionChecked by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                || permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        permissionChecked = true
        if (permissionGranted) onGranted() else onDenied()
    }

    // Check on first composition; launch dialog if needed
    LaunchedEffect(Unit) {
        permissionGranted = PermissionUtil.hasLocationPermission(context)
        if (permissionGranted) {
            permissionChecked = true
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

    when {
        // Permission confirmed — show real content
        permissionGranted -> content()

        // Dialog was dismissed with denial — content layer handles the state
        permissionChecked && !permissionGranted -> content()

    }
}

/*
@Composable
fun weatherBackground(weatherType: WeatherType): Color {
    return when (weatherType) {
        WeatherType.SUNNY -> Color(0xFFFFC107)
        WeatherType.CLOUDY -> Color(0xFF90A4AE)
        WeatherType.RAINY -> Color(0xFF546E7A)
    }
}*/

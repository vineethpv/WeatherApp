package com.vpvn.weatherapp.ui.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vpvn.weatherapp.R
import com.vpvn.weatherapp.domain.model.WeatherForecast

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecasts: List<WeatherForecast>,
    contentPaddingValues: PaddingValues
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(key = "forecast_header") {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string._5_day_forecast),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
        // One card per day with a 80 ms stagger between cards
        itemsIndexed(
            items = forecasts,
            key = { _, item -> item.date }
        ) { index, forecast ->
            ForecastCard(
                forecast = forecast,
                animationDelayMs = index * 80
            )
        }
    }

}
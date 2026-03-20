package com.vpvn.weatherapp.ui.forecast

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vpvn.weatherapp.domain.model.WeatherForecast

@Composable
fun ForecastCard(
    modifier: Modifier = Modifier,
    forecast: WeatherForecast,
    animationDelayMs: Int = 0
) {

    // Fade-in state, triggered once on first composition
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 400, delayMillis = animationDelayMs),
        label = "cardAlpha_${forecast.date}"
    )
    val translationY by animateFloatAsState(
        targetValue = if (visible) 0f else 30f,
        animationSpec = tween(durationMillis = 400, delayMillis = animationDelayMs),
        label = "cardSlide_${forecast.date}"
    )

    LaunchedEffect(Unit) { visible = true }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 8.dp)
            .graphicsLayer(
                alpha = alpha,
                translationY = translationY
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = forecast.date,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherIcon(
                    iconCode = forecast.iconCode,
                    size = 48.dp
                )

                Text(
                    text = "${forecast.temperature}°",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun WeatherIcon(
    iconCode: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = "https://openweathermap.org/img/wn/${iconCode}@2x.png",
        contentDescription = "Weather icon for $iconCode",
        contentScale = ContentScale.Fit,
        modifier = modifier.size(size)
    )
}


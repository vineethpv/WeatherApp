package com.vpvn.designsystem.foundation.elevation

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ElevationTokens(
    val none: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp
)

val DefaultElevationTokens = ElevationTokens(
    none = 0.dp,
    xs = 1.dp,
    sm = 2.dp,
    md = 4.dp,
    lg = 8.dp,
    xl = 16.dp
)
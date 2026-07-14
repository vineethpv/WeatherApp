package com.vpvn.designsystem.foundation.colors

import androidx.compose.ui.graphics.Color

object ColorPalette {

    // Brand
    val Blue500 = Color(0xFF1976D2)
    val Blue700 = Color(0xFF1565C0)
    val Red500 = Color(0xFFD32F2F)

    // Neutral
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    val Gray50 = Color(0xFFFAFAFA)
    val Gray100 = Color(0xFFF5F5F5)
    val Gray300 = Color(0xFFE0E0E0)
    val Gray500 = Color(0xFF9E9E9E)
    val Gray600 = Color(0xFF757575)
    val Gray900 = Color(0xFF212121)

    val LightColorTokens = ColorTokens(
        primary = Blue500,
        onPrimary = White,

        secondary = Blue700,
        onSecondary = White,

        background = White,
        surface = Gray100,

        error = Red500,
        onError = White,

        textPrimary = Gray900,
        textSecondary = Gray600,

        outline = Gray300
    )

    val DarkColorTokens = ColorTokens(
        primary = Blue500,
        onPrimary = White,

        secondary = Blue700,
        onSecondary = White,

        background = Black,
        surface = Color(0xFF1E1E1E),

        error = Red500,
        onError = White,

        textPrimary = White,
        textSecondary = Color(0xFFB0B0B0),

        outline = Gray500
    )
}
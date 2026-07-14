package com.vpvn.designsystem.foundation.colors

import androidx.compose.ui.graphics.Color

object ColorPalette {

    // Brand colors
    val Blue500 = Color(0xFF1976D2)
    val Blue700 = Color(0xFF1565C0)
    val Gray100 = Color(0xFFF5F5F5)
    val Gray900 = Color(0xFF212121)
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Red500 = Color(0xFFD32F2F)

    val LightColorTokens = ColorTokens(
        primary = Blue500,
        secondary = Blue700,
        background = White,
        surface = Gray100,
        error = Red500,
        textPrimary = Black,
        textSecondary = Color(0xFF666666)
    )

    val DarkColorTokens = ColorTokens(
        primary = Blue500,
        secondary = Blue700,
        background = Black,
        surface = Color(0xFF1E1E1E),
        error = Red500,
        textPrimary = White,
        textSecondary = Color(0xFFB0B0B0)
    )
}
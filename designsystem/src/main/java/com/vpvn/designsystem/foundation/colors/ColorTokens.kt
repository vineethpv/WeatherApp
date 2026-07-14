package com.vpvn.designsystem.foundation.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorTokens(
    val primary: Color,
    val onPrimary: Color,

    val secondary: Color,
    val onSecondary: Color,

    val background: Color,
    val surface: Color,

    val error: Color,
    val onError: Color,

    val textPrimary: Color,
    val textSecondary: Color,

    val outline: Color
)
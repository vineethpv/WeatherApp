package com.vpvn.designsystem.foundation.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorTokens(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val error: Color,
    val textPrimary: Color,
    val textSecondary: Color
)
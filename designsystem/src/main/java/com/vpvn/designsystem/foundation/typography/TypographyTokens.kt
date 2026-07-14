package com.vpvn.designsystem.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class TypographyTokens(
    val headingLarge: TextStyle,
    val headingMedium: TextStyle,
    val body: TextStyle,
    val caption: TextStyle
)

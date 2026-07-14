package com.vpvn.designsystem.foundation.tokens

import androidx.compose.runtime.Immutable
import com.vpvn.designsystem.foundation.colors.ColorTokens
import com.vpvn.designsystem.foundation.elevation.ElevationTokens
import com.vpvn.designsystem.foundation.shapes.ShapeTokens
import com.vpvn.designsystem.foundation.spacing.SpacingTokens
import com.vpvn.designsystem.foundation.typography.TypographyTokens

@Immutable
data class DesignTokens(
    val colors: ColorTokens,
    val typography: TypographyTokens,
    val shapes: ShapeTokens,
    val spacing: SpacingTokens,
    val elevation: ElevationTokens
)
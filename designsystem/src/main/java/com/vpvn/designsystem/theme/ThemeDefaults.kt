package com.vpvn.designsystem.theme

import com.vpvn.designsystem.foundation.colors.ColorPalette.DarkColorTokens
import com.vpvn.designsystem.foundation.colors.ColorPalette.LightColorTokens
import com.vpvn.designsystem.foundation.elevation.DefaultElevationTokens
import com.vpvn.designsystem.foundation.shapes.DefaultShapeTokens
import com.vpvn.designsystem.foundation.spacing.DefaultSpacingTokens
import com.vpvn.designsystem.foundation.tokens.DesignTokens
import com.vpvn.designsystem.foundation.typography.Typography.AppTypography

object ThemeDefaults {

    val Light = DesignTokens(
        colors = LightColorTokens,
        typography = AppTypography,
        shapes = DefaultShapeTokens,
        spacing = DefaultSpacingTokens,
        elevation = DefaultElevationTokens
    )

    val Dark = DesignTokens(
        colors = DarkColorTokens,
        typography = AppTypography,
        shapes = DefaultShapeTokens,
        spacing = DefaultSpacingTokens,
        elevation = DefaultElevationTokens
    )
}
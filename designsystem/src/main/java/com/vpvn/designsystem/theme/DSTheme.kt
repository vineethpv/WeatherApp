package com.vpvn.designsystem.theme

import androidx.compose.runtime.Composable
import com.vpvn.designsystem.theme.CompositionLocals.LocalTokens

object DSTheme {

    val colors
        @Composable
        get() = LocalTokens.current.colors

    val typography
        @Composable
        get() = LocalTokens.current.typography

    val spacing
        @Composable
        get() = LocalTokens.current.spacing

    val shapes
        @Composable
        get() = LocalTokens.current.shapes

    val elevation
        @Composable
        get() = LocalTokens.current.elevation

    @Composable
    operator fun invoke(
        darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        DSThemeProvider(darkTheme, content)
    }
}
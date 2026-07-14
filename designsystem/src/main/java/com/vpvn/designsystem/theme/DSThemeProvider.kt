package com.vpvn.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.vpvn.designsystem.theme.CompositionLocals.LocalTokens


@Composable
internal fun DSThemeProvider(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val themeTokens = if (darkTheme) {
        ThemeDefaults.Dark
    } else {
        ThemeDefaults.Light
    }

    CompositionLocalProvider(LocalTokens provides themeTokens) {
        content()
    }
}
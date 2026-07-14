package com.vpvn.designsystem.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vpvn.designsystem.theme.DSTheme

object DSButtonDefaults {

    @Composable
    fun primaryColors(): DSButtonColors {
        val colors = DSTheme.colors

        return DSButtonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.surface,
            disabledContentColor = colors.textSecondary
        )
    }

    @Composable
    fun secondaryColors(): DSButtonColors {
        val colors = DSTheme.colors

        return DSButtonColors(
            containerColor = colors.secondary,
            contentColor = colors.onSecondary,
            disabledContainerColor = colors.surface,
            disabledContentColor = colors.textSecondary
        )
    }

    @Composable
    fun outlineColors(): DSButtonColors {
        val colors = DSTheme.colors

        return DSButtonColors(
            containerColor = colors.background,
            contentColor = colors.primary,
            disabledContainerColor = colors.background,
            disabledContentColor = colors.textSecondary
        )
    }

    @Composable
    fun textColors(): DSButtonColors {
        val colors = DSTheme.colors

        return DSButtonColors(
            containerColor = colors.background,
            contentColor = colors.primary,
            disabledContainerColor = colors.background,
            disabledContentColor = colors.textSecondary
        )
    }

    @Composable
    fun destructiveColors(): DSButtonColors {
        val colors = DSTheme.colors

        return DSButtonColors(
            containerColor = colors.error,
            contentColor = colors.onError,
            disabledContainerColor = colors.surface,
            disabledContentColor = colors.textSecondary
        )
    }

    @Composable
    fun colors(
        variant: ButtonVariant
    ): DSButtonColors =
        when (variant) {
            ButtonVariant.Primary -> primaryColors()
            ButtonVariant.Secondary -> secondaryColors()
            ButtonVariant.Outline -> outlineColors()
            ButtonVariant.Text -> textColors()
            ButtonVariant.Destructive -> destructiveColors()
        }

    fun elevation() = DSButtonElevation(
        default = 2.dp,
        pressed = 6.dp,
        focused = 4.dp,
        hovered = 3.dp,
        disabled = 0.dp
    )
}
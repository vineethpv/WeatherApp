package com.vpvn.designsystem.components.button.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vpvn.designsystem.components.button.ButtonVariant
import com.vpvn.designsystem.components.button.DSButtonDefaults
import com.vpvn.designsystem.theme.DSTheme

// Enhancements : Introduce a ButtonStyleFactory or registry where each ButtonVariant maps to a ButtonStyleProvider.
internal object ButtonStyleResolver {

    @Composable
    fun resolve(
        variant: ButtonVariant
    ): ButtonStyle {
        return when (variant) {
            ButtonVariant.Primary -> {

                ButtonStyle(
                    type = ButtonType.Filled,
                    colors = DSButtonDefaults.primaryColors(),
                    elevation = DSButtonDefaults.elevation(),
                    shape = DSTheme.shapes.medium
                )
            }

            ButtonVariant.Secondary ->

                ButtonStyle(
                    type = ButtonType.Filled,
                    colors = DSButtonDefaults.secondaryColors(),
                    elevation = DSButtonDefaults.elevation(),
                    shape = DSTheme.shapes.medium
                )

            ButtonVariant.Outline ->

                ButtonStyle(
                    type = ButtonType.Outlined,
                    colors = DSButtonDefaults.outlineColors(),
                    elevation = DSButtonDefaults.elevation(),
                    shape = DSTheme.shapes.medium,
                    border = BorderStroke(
                        1.dp,
                        DSButtonDefaults.outlineColors().contentColor
                    )
                )

            ButtonVariant.Text ->

                ButtonStyle(
                    type = ButtonType.Text,
                    colors = DSButtonDefaults.textColors(),
                    elevation = DSButtonDefaults.elevation(),
                    shape = DSTheme.shapes.medium
                )

            ButtonVariant.Destructive ->

                ButtonStyle(
                    type = ButtonType.Filled,
                    colors = DSButtonDefaults.destructiveColors(),
                    elevation = DSButtonDefaults.elevation(),
                    shape = DSTheme.shapes.medium
                )
        }
    }
}
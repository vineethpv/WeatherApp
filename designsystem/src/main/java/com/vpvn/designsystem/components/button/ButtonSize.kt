package com.vpvn.designsystem.components.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ButtonSize(
    val height: Dp,
    val horizontalPadding: Dp,
    val iconSize: Dp
) {

    companion object {

        val Small = ButtonSize(
            height = 36.dp,
            horizontalPadding = 12.dp,
            iconSize = 16.dp
        )

        val Medium = ButtonSize(
            height = 48.dp,
            horizontalPadding = 16.dp,
            iconSize = 20.dp
        )

        val Large = ButtonSize(
            height = 56.dp,
            horizontalPadding = 20.dp,
            iconSize = 24.dp
        )
    }
}
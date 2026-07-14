package com.vpvn.designsystem.components.button.internal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vpvn.designsystem.components.button.ButtonSize

@Composable
internal fun ButtonRenderer(
    style: ButtonStyle,
    modifier: Modifier,
    enabled: Boolean,
    size: ButtonSize,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {

    val colors = ButtonDefaults.buttonColors(
        containerColor = style.colors.containerColor,
        contentColor = style.colors.contentColor,
        disabledContainerColor = style.colors.disabledContainerColor,
        disabledContentColor = style.colors.disabledContentColor
    )

    val elevation = ButtonDefaults.buttonElevation(
        defaultElevation = style.elevation.default,
        pressedElevation = style.elevation.pressed,
        focusedElevation = style.elevation.focused,
        hoveredElevation = style.elevation.hovered,
        disabledElevation = style.elevation.disabled
    )

    when (style.type) {
        ButtonType.Filled -> {
            Button(
                onClick = onClick,
                modifier = modifier.height(size.height),
                enabled = enabled,
                colors = colors,
                elevation = elevation,
                shape = style.shape,
                contentPadding = PaddingValues(horizontal = size.horizontalPadding),
                content = content
            )
        }

        ButtonType.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier.height(size.height),
                enabled = enabled,
                colors = colors,
                border = style.border,
                shape = style.shape,
                contentPadding = PaddingValues(
                    horizontal = size.horizontalPadding
                ),
                content = content
            )
        }

        ButtonType.Text -> {
            TextButton(
                onClick = onClick,
                modifier = modifier.height(size.height),
                enabled = enabled,
                colors = colors,
                shape = style.shape,
                contentPadding = PaddingValues(
                    horizontal = size.horizontalPadding
                ),
                content = content
            )
        }
    }
}
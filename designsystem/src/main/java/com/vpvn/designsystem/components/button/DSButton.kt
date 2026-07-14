package com.vpvn.designsystem.components.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vpvn.designsystem.components.button.internal.ButtonRenderer
import com.vpvn.designsystem.components.button.internal.ButtonStyleResolver

@Composable
fun DSButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Medium,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {

    val style = ButtonStyleResolver.resolve(variant)

    ButtonRenderer(
        style = style,
        modifier = modifier,
        enabled = enabled,
        size = size,
        onClick = onClick,
        content = content
    )

}
package com.vpvn.designsystem.components.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class DSButtonColors(

    val containerColor: Color,

    val contentColor: Color,

    val disabledContainerColor: Color,

    val disabledContentColor: Color
)
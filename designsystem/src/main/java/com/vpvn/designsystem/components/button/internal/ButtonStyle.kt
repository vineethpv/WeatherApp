package com.vpvn.designsystem.components.button.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import com.vpvn.designsystem.components.button.DSButtonColors
import com.vpvn.designsystem.components.button.DSButtonElevation

@Immutable
internal data class ButtonStyle(
    val type: ButtonType,

    val colors: DSButtonColors,

    val elevation: DSButtonElevation,

    val shape: Shape,

    val border: BorderStroke? = null
)
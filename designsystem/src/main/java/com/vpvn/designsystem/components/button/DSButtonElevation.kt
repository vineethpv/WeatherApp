package com.vpvn.designsystem.components.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class DSButtonElevation(

    val default: Dp,

    val pressed: Dp,

    val focused: Dp,

    val hovered: Dp,

    val disabled: Dp
)
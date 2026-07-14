package com.vpvn.designsystem.components.button

sealed interface ButtonVariant {

    data object Primary : ButtonVariant
    data object Secondary : ButtonVariant
    data object Outline : ButtonVariant
    data object Destructive : ButtonVariant
    data object Text : ButtonVariant
}
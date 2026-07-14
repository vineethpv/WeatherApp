package com.vpvn.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.vpvn.designsystem.foundation.tokens.DesignTokens

object CompositionLocals {

    val LocalTokens = staticCompositionLocalOf<DesignTokens> {
        error("No design tokens provided")
    }
}
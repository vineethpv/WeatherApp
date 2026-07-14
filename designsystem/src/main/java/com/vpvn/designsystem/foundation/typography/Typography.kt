package com.vpvn.designsystem.foundation.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


object Typography {

    val AppTypography = TypographyTokens(
        headingLarge = TextStyle(
            fontFamily = FontFamilyProvider.Primary,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        headingMedium = TextStyle(
            fontFamily = FontFamilyProvider.Primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        ),
        body = TextStyle(
            fontFamily = FontFamilyProvider.Primary,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        caption = TextStyle(
            fontFamily = FontFamilyProvider.Primary,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )
}
package com.ashish.locationtracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF5A87FF),
    primaryVariant = Color(0xFFA6B5CF),
    secondary = Color(0xFFADCBB8) ,
    background = Color(0xFFf9f9f9),
    surface = Color(0xFFD9E2F6),
    onPrimary = Color(0xFF373F3B),
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF5A87FF),
    primaryVariant = Color(0xFFA6B5CF),
    secondary = Color(0xFFADCBB8) ,
    background = Color(0xFFf9f9f9),
    surface = Color(0xFFD9E2F6),
    onPrimary = Color(0xFF373F3B),
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun LocationTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
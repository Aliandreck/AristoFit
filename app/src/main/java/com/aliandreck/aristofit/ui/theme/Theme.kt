package com.aliandreck.aristofit.ui.theme

import androidx.compose.ui.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


val RoyalPurple = Color(0xFF833AB4)
val DeepPurple = Color(0xFF7632A4)
val Silver = Color(0xFFD09DF1)
val RichBlack = Color(0xFFF1B37E)
val Charcoal = Color(0xFFDD2A7B)
val White = Color(0xFFDE98CD)
val LightGray = Color(0xFFF5C1C1)


private val DarkColorScheme = darkColorScheme(
    primary = RoyalPurple1,
    secondary = Silver1,
    tertiary = DeepPurple1,
    background = RichBlack1,
    surface = Charcoal1,
    onPrimary = White1,
    onSecondary = White1,
    onTertiary = White1,
    onBackground = LightGray1,
    onSurface = LightGray1
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalPurple1,
    secondary = Silver1,
    tertiary = DeepPurple1,
    background = White1,
    surface = LightGray1,
    onPrimary = White1,
    onSecondary = RichBlack1,
    onTertiary = RichBlack1,
    onBackground = RichBlack1,
    onSurface = RichBlack1
)

@Composable
fun DripVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Turned off for brand consistency
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
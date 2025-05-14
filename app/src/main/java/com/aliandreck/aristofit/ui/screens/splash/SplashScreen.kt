package com.aliandreck.aristofit.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.navigation.ROUT_LOGIN
import com.aliandreck.aristofit.ui.theme.RichBlack
import com.aliandreck.aristofit.ui.theme.RoyalPurple
import com.aliandreck.aristofit.ui.theme.Silver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.aliandreck.aristofit.R

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    val coroutine = rememberCoroutineScope()

    // Animation states
    val logoScale = remember { Animatable(0.8f) }
    val logoAlpha = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }

    // Launch animations
    LaunchedEffect(Unit) {
        // Parallel animations
        launch {
            logoAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1500)
            )
        }
        launch {
            logoScale.animateTo(
                targetValue = 1.05f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
        launch {
            delay(1000) // Staggered start
            textAlpha.animateTo(1f, tween(durationMillis = 2500))
        }

        // Navigation after delay
        delay(4500)
        navController.navigate(ROUT_LOGIN) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RichBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.dvlogo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(200.dp)
                .scale(logoScale.value)
                .alpha(logoAlpha.value)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ARISTOFIT",
            fontSize = 42.sp,
            fontWeight = FontWeight.ExtraBold,
            color = RoyalPurple,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp,
            modifier = Modifier.alpha(textAlpha.value)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "FASHION & LUXURY,IN YOUR PALMS!!!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Silver,
            letterSpacing = 1.sp,
            modifier = Modifier.alpha(textAlpha.value)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}
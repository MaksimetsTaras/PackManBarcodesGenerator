package com.example.packmanbarcodesgenerator

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) =
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        val scale = remember { Animatable(0.0f) }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,//zoom of image
                animationSpec = tween(
                    1000,
                    easing = LinearOutSlowInEasing)
            )
//            delay(1000)


            navController.navigate(Screens.Main) {
                popUpTo(Screens.Splash) {
                    inclusive = true
                }
            }
        }
        Image(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value),
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = null,
            alignment = Alignment.Center
        )
    }
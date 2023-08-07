package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.packmanbarcodesgenerator.screens.BoxScreen
import com.example.packmanbarcodesgenerator.screens.PartScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            val bottomNavItems = arrayListOf<bottomNavItem>()
            bottomNavItems.add(bottomNavItem("Box", "Box", R.drawable.box))
            bottomNavItems.add(bottomNavItem("Part", "Part", R.drawable.part))

            BottomNavigation(
                backgroundColor = colorResource(id = R.color.purple_200),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            ) {
                bottomNavItems.forEach { it ->
                    val selected = it.route == backStackEntry.value?.destination?.route

                    BottomNavigationItem(
                        selected = selected,
                        alwaysShowLabel = false,
                        onClick = { navController.navigate(it.route) },
                        label = { Text(it.name) },
                        selectedContentColor = Color.Transparent,
                        unselectedContentColor = Color.Blue,
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = it.name
                            )
                        })
                }
            }
        }
    ) {

        NavHost(navController = navController, startDestination = "Box") {
            composable("Box") {
                BoxScreen()
            }

            composable("Part") {
                PartScreen()
            }
        }
    }
}
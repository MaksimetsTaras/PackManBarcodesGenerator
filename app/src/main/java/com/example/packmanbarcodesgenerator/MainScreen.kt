package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.packmanbarcodesgenerator.BottomNav.BottomNavItem
import com.example.packmanbarcodesgenerator.screens.BoxScreen
import com.example.packmanbarcodesgenerator.screens.PartScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val bottomNavItems = listOf("Box", "Part")

            BottomNavigation() {
                bottomNavItems.forEach { screen ->
                    BottomNavigationItem(
                        selected = false,
                        onClick = { navController.navigate(screen) },
                        label = { Text(screen) },
                        icon = {})
                }
            }
        }) {

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

//    @Composable
//    fun BottomNavigationBar() {
//
//        var selectedItem by remember { mutableStateOf(0) }
//
////        val bottomNavItems = listOf(
////            BottomNavItem(
////                name = "Box",
////                route = "home",
////                icon = R.drawable.box,
////            ),
////            BottomNavItem(
////                name = "Switch",
////                route = "add",
////                icon = R.drawable.part,
////            ),
////        )
//
//        val bottomNavItems = listOf("Box", "Part")
//
//        BottomNavigation() {
//            bottomNavItems.forEach { screen ->
//                BottomNavigationItem(
//                    selected = false,
//                    onClick = { navController.navigate(screen) },
//                    label = { Text(screen) },
//                    icon = {})
//            }
//        }
//
////        NavigationBar {
////            bottomNavItems.forEachIndexed { index, item ->
////
////                NavigationBarItem(
////                    icon = {
////                        Icon(
////                            painterResource(id = item.icon),
////                            contentDescription = "Person Icon"
////                        )
////                    },
////                    label = { Text(item.name) },
////                    selected = selectedItem == index,
////                    onClick = {
////                        selectedItem = index
////                        navController.navigate()
////                    },
////                )
////            }
////        }
//    }

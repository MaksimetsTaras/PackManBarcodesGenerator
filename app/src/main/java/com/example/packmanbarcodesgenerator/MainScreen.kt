package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.packmanbarcodesgenerator.screens.BoxScreen
import com.example.packmanbarcodesgenerator.screens.PartScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    var bottomNavHeight by remember { mutableStateOf(0.dp) }
    var topAppHeight by remember { mutableStateOf(0.dp) }
    // get local density from composable
    val density = LocalDensity.current

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .onGloballyPositioned {
                        topAppHeight = with(density) {
                            it.size.height.toDp()
                        }
                    }
                    .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)),
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200),
                    titleContentColor = colorResource(id = R.color.black),
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                },
                actions = {
                    val iconSave = painterResource(id = R.drawable.save)

                    IconButton(onClick = { makeToast(context, "Save") }) {
                        Icon(painter = iconSave, contentDescription = null)
                    }
//                    IconButton(onClick = { makeToast(context, "Save") }) {
//                        Icon(painter = icon2, contentDescription = null)
//                    }
                },
            )
        },
        bottomBar = {
            val bottomNavItems = arrayListOf<bottomNavItem>()
            bottomNavItems.add(bottomNavItem("Box", "Box", R.drawable.box))
            bottomNavItems.add(bottomNavItem("Part", "Part", R.drawable.part))

            BottomNavigation(
                backgroundColor = colorResource(id = R.color.purple_200),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                    .onGloballyPositioned {
                        bottomNavHeight = with(density) {
                            it.size.height.toDp()
                        }
                    }
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

        NavHost(
            navController = navController,
            startDestination = "Box",
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomNavHeight, top = topAppHeight)
        ) {
            composable("Box") {
                BoxScreen()
            }

            composable("Part") {
                PartScreen()
            }
        }
    }
}

fun makeToast(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}
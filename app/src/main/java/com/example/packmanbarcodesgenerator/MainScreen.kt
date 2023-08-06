package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.packmanbarcodesgenerator.BottomNav.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    Scaffold(
        bottomBar = { BottomNavigationBar() }) {
        Text("Hello Scaffold", fontSize = 28.sp)
    }
}
@Composable
fun BottomNavigationBar() {

    var selectedItem by remember { mutableStateOf(0) }

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Box",
            route = "home",
            icon = R.drawable.box,
        ),
        BottomNavItem(
            name = "Switch",
            route = "add",
            icon = R.drawable.part,
        ),
    )

    NavigationBar {
        bottomNavItems.forEachIndexed { index, item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = "Person Icon"
                    )
                },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
            )
        }
    }
}
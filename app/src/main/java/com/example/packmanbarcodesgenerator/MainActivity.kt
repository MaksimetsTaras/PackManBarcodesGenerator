package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.packmanbarcodesgenerator.BottomNav.BottomNavItem
import com.example.packmanbarcodesgenerator.ui.theme.PackManBarcodesGeneratorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackManBarcodesGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ShowMainScreen() {
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar() }) {
//        Text("Hello Scaffold", fontSize = 28.sp)
//    }
//}



//
//
//
//
//        TextField(value = "SWITCH", onValueChange = {})
//        val sss: String = "/P283E815550R/3OS1054451-07/SN9336112345678"
//        Image(
//            painter = barcodeGenerator.createQRcode(sss),
//            contentDescription = "DEV Communit Code",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.size(135.dp),
//        )
//
//
//    }
//}




//@Composable
//fun BottomNavigationBar() {
//
//    var selectedItem by remember { mutableStateOf(0) }
//
//    val bottomNavItems = listOf(
//        BottomNavItem(
//            name = "Box",
//            route = "home",
//            icon = R.drawable.box,
//        ),
//        BottomNavItem(
//            name = "Switch",
//            route = "add",
//            icon = R.drawable.part,
//        ),
//    )

//    BottomNavigationBar()
//    NavigationBar {
//        bottomNavItems.forEachIndexed { index, item ->
//
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        painterResource(id = item.icon),
//                        contentDescription = "Person Icon"
//                    )
//                },
//                label = { Text(item.name) },
//                selected = selectedItem == index,
//                onClick = { selectedItem = index },
//            )
//        }
//    }

//}
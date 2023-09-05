package com.example.packmanbarcodesgenerator

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barcodeGenerator.BoxQRcode
import com.example.packmanbarcodesgenerator.screens.BoxScreen
import com.example.packmanbarcodesgenerator.screens.PartScreen
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    //JOINT
    var articleWWW = remember { mutableStateOf(String) }

    var article = remember { mutableStateOf(TextFieldValue("10544017")) }
    var index = remember { mutableStateOf(TextFieldValue("00")) }
    var customerArticle = remember { mutableStateOf(TextFieldValue("A1749055601")) }
    //BOX
    val packaging = remember { mutableStateOf(TextFieldValue("453940087")) }
    val quantityInBox = remember { mutableStateOf(TextFieldValue("10")) }
    val batchNumber = remember { mutableStateOf(TextFieldValue("720716")) }
    //PART
    var HWversionPART = remember { mutableStateOf(TextFieldValue("21.1")) }
    var SWversionPART = remember { mutableStateOf(TextFieldValue("8.1")) }
    var serialNumberPART = remember { mutableStateOf(TextFieldValue("94288WGI00081")) }
    //OTHER
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    var bottomNavHeight by remember { mutableStateOf(0.dp) }
    var topAppHeight by remember { mutableStateOf(0.dp) }
    // get local density from composable
    val density = LocalDensity.current
    val context = LocalContext.current

    val (fabOnClick, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null) }

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            backgroundColor = Color.Transparent,
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
                    navigationIcon = {
                        val iconSave = painterResource(id = R.drawable.load)

                        IconButton(onClick = {

                            val activeBottomItem: String =
                                backStackEntry.value?.destination?.route.toString()

//                            makeToast(context, activeBottomItem)
//
                            if (activeBottomItem == "Box") {
                                var currentBox = loadFromSharedPreferences(context)

                                val text = currentBox.article
                                article.value = article.value.copy(
                                    annotatedString = AnnotatedString("sss", ParagraphStyle(null)),
                                    selection = TextRange(0, text.length),
                                    composition = TextRange(0, text.length)
                                )


                            } else {

                            }

//                            val currentBox = BoxQRcode(
//                                packaging.value.toString(),
//                                article.value.toString(),
//                                index.value.toString(),
//                                quantityInBox.value.toString(),
//                                batchNumber.value.toString(),
//                                customerArticle.value.toString()
//                            )
//
//                            saveToSharedPreferences(context, currentBox)
                        }) {
                            Icon(painter = iconSave, contentDescription = null)
                        }
                    },
                    actions = {
                        val iconSave = painterResource(id = R.drawable.save)

                        IconButton(onClick = {
                            makeToast(context, "Save")

                            val currentBox = BoxQRcode(
                                packaging.value.toString(),
                                article.value.toString(),
                                index.value.toString(),
                                quantityInBox.value.toString(),
                                batchNumber.value.toString(),
                                customerArticle.value.toString()
                            )

                            saveToSharedPreferences(context, currentBox)
                        }) {
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

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    bottomNavItems.forEach { it ->
                        val selected = it.route == backStackEntry.value?.destination?.route

                        BottomNavigationItem(
                            selected = selected,
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
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
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    fabOnClick?.invoke()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.barcode),
                        contentDescription = ""
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
        ) {
            NavHost(
                navController = navController,
                startDestination = "Box",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = bottomNavHeight + (bottomNavHeight / 2))
            ) {
                composable("Box") {
                    BoxScreen(
                        setFabOnClick = setFabOnClick,
                        article,
                        index,
                        packaging,
                        quantityInBox,
                        batchNumber,
                        customerArticle
                    )
                }
                composable("Part") {
                    PartScreen(
                        setFabOnClick = setFabOnClick,
                        article,
                        index,
                        customerArticle,
                        HWversionPART,
                        SWversionPART,
                        serialNumberPART
                    )
                }
            }
        }
    }
}

fun makeToast(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}

fun saveToSharedPreferences(ctx: Context, currentBox: BoxQRcode) {
    val myShared = mySharedPreferences.mySharedPreferences(ctx)

    val gson = Gson()
    val json: String = gson.toJson(currentBox)

    myShared.saveBoxValues(json)

}

fun loadFromSharedPreferences(ctx: Context): BoxQRcode {
    val myShared = mySharedPreferences.mySharedPreferences(ctx)

    val boxString: String = myShared.getPersNumber()

    val gson = Gson()

    val box: BoxQRcode = gson.fromJson(boxString, BoxQRcode::class.java)

    return box
}
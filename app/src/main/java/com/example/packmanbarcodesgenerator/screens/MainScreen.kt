package com.example.packmanbarcodesgenerator.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barcodeGenerator.BoxQRcode
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.R
import com.example.packmanbarcodesgenerator.BottomNavItem
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    //JOINT
    val article = remember { mutableStateOf("10544017") }
    val index = remember { mutableStateOf("00") }
    val customerArticle = remember { mutableStateOf("A1749055601") }
    //BOX
    val packaging = remember { mutableStateOf("453940087") }
    val quantityInBox = remember { mutableStateOf("10") }
    val batchNumber = remember { mutableStateOf("720716") }
    //PART
    val HWversionPART = remember { mutableStateOf("21.1") }
    val SWversionPART = remember { mutableStateOf("8.1") }
    val serialNumberPART = remember { mutableStateOf("94288WGI00081") }
    val isHWpresent = remember { mutableStateOf(true) }
    val isSWpresent = remember { mutableStateOf(true) }
    //OTHER


    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    var bottomNavHeight by remember { mutableStateOf(0.dp) }
//    var topAppHeight by remember { mutableStateOf(0.dp) }
//    // get local density from composable
    val density = LocalDensity.current
//    val context = LocalContext.current

    val (fabOnClick, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null) }

    val openDialogForLoad = remember { mutableStateOf(false) }





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
                MainScreenTopBar(
                    openDialogForLoad,
                    backStackEntry,
                    article,
                    index,
                    customerArticle,
                    HWversionPART,
                    SWversionPART,
                    serialNumberPART,
                    isHWpresent,
                    isSWpresent,
                    packaging,
                    quantityInBox,
                    batchNumber
                )
            },
            bottomBar = {
                val bottomNavItems = arrayListOf<BottomNavItem>()
                bottomNavItems.add(BottomNavItem("Box", "Box", R.drawable.box))
                bottomNavItems.add(BottomNavItem("Part", "Part", R.drawable.part))

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

//                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    bottomNavItems.forEach { it ->
                        val selected = it.route == getActiveBottomItem(backStackEntry)

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
                        serialNumberPART,
                        isHWpresent,
                        isSWpresent
                    )
                }
            }
        }
    }
}

fun makeToast(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}

fun saveToSharedPreferences(
    ctx: Context,
    dataBox: BoxQRcode = BoxQRcode("", "", "", "", "", ""),
    dataPart: PartQRcode = PartQRcode("", "", "", "", "", "", true, true)
) {
    val myShared = mySharedPreferences.mySharedPreferences(ctx)
    val gson = Gson()

    var json = ""
    var key = ""
    if (dataBox.article.isNotEmpty()) {
        json = gson.toJson(dataBox)
        key = BottomItems.Box.name
    } else if (dataPart.article.isNotEmpty()) {
        json = gson.toJson(dataPart)
        key = BottomItems.Part.name
    }

    myShared.saveRecord(key, json)
}

inline fun <reified T> loadFromSharedPreferences(
    ctx: Context,
    activeBottomItem: String
): ArrayList<T> {
    val myShared = mySharedPreferences.mySharedPreferences(ctx)

    //Get all records fron SharedPreferences
    var allNeededRecords = mutableListOf<String>()
    if (activeBottomItem == BottomItems.Box.name) {
        allNeededRecords = myShared.getAllValues_WhereKeyStartsWith(BottomItems.Box.name)
    } else if (activeBottomItem == BottomItems.Part.name) {
        allNeededRecords = myShared.getAllValues_WhereKeyStartsWith(BottomItems.Part.name)
    }

    //Convert to needed type
    val gson = Gson()

    val result: ArrayList<T> = ArrayList<T>()
    for (record in allNeededRecords) {
        val convertedRecord: T = gson.fromJson(record, T::class.java)
        result.add(convertedRecord)
    }
    return result
}

fun setValueToMutableInstance(element: MutableState<TextFieldValue>, valueToWrite: String) {
    element.value = element.value.copy(
        annotatedString = AnnotatedString(valueToWrite, ParagraphStyle(null)),
//        selection = TextRange(0, text.length),
//        composition = TextRange(0, text.length)
    )
}

fun getActiveBottomItem(backStackEntry: State<NavBackStackEntry?>): String {
    return backStackEntry.value?.destination?.route.toString()
}

enum class BottomItems() {
    Box, Part
}

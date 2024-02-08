package com.example.packmanbarcodesgenerator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.navigation.NavBackStackEntry
import barcodeGenerator.BoxQRcode
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.R
import com.example.packmanbarcodesgenerator.uiElements.CustomListView.CustomAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    openDialogForLoad: MutableState<Boolean>,
    backStackEntry: State<NavBackStackEntry?>,
    article: MutableState<String>,
    index: MutableState<String>,
    customerArticle: MutableState<String>,
    HWversionPART: MutableState<String>,
    SWversionPART: MutableState<String>,
    serialNumberPART: MutableState<String>,
    isHWpresent: MutableState<Boolean>,
    isSWpresent: MutableState<Boolean>,
    packaging: MutableState<String>,
    quantityInBox: MutableState<String>,
    batchNumber: MutableState<String>
) {

    var topAppHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val context = LocalContext.current

    var listOfSavedRecordsBox: ArrayList<BoxQRcode> = ArrayList()
    var listOfSavedRecordsPart: ArrayList<PartQRcode> = ArrayList()

    val listOfCheckedItems = remember { mutableStateListOf<Int>() }

    val fieldsForPart = mapOf(
        "article" to article,
        "index" to index,
        "customerArticle" to customerArticle,
        "HWversionPART" to HWversionPART,
        "SWversionPART" to SWversionPART,
        "serialNumberPART" to serialNumberPART
    )
    val fieldsForPartHWandSW = mapOf(
        "isHWpresent" to isHWpresent,
        "isSWpresent" to isSWpresent
    )

    val fieldsForBox = mapOf(
        "article" to article,
        "index" to index,
        "customerArticle" to customerArticle,
        "packaging" to packaging,
        "quantityInBox" to quantityInBox,
        "batchNumber" to batchNumber
    )


    TopAppBar(
        modifier = Modifier
            .onGloballyPositioned {
                topAppHeight = with(density) {
                    it.size.height.toDp()
                }
            }
            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.purple_200),
            titleContentColor = colorResource(id = R.color.black),
        ),
        title = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.app_name))
            }
        },
        navigationIcon = {
            val iconSave = painterResource(id = R.drawable.load)

            IconButton(onClick = {
                openDialogForLoad.value = true
            }) {
                Icon(painter = iconSave, contentDescription = null)
            }
            if (openDialogForLoad.value) {

                //Download
                val activeBottomItem: String = getActiveBottomItem(backStackEntry)

                if (activeBottomItem == BottomItems.Box.name) {
                    listOfSavedRecordsBox =
                        loadFromSharedPreferences(context, BottomItems.Box.name)

                    if (listOfSavedRecordsBox.isEmpty()) {
                        makeToast(context, "Не має елементів для завантаження")
                        openDialogForLoad.value = false
                    }

                } else if (activeBottomItem == BottomItems.Part.name) {
                    listOfSavedRecordsPart =
                        loadFromSharedPreferences(context, BottomItems.Part.name)

                    if (listOfSavedRecordsPart.isEmpty()) {
                        makeToast(context, "Не має елементів для завантаження")
                        openDialogForLoad.value = false
                    }
                }
            }

            if (openDialogForLoad.value) {
                listOfCheckedItems.clear()
                val activeBottomItem: String = getActiveBottomItem(backStackEntry)
                CustomAlertDialog(
                    openDialogForLoad,
                    activeBottomItem,
                    fieldsForPart = fieldsForPart,
                    fieldsForPartHWandSW = fieldsForPartHWandSW,
                    fieldsForBox = fieldsForBox,
                    if (activeBottomItem == BottomItems.Box.name) listOfSavedRecordsBox else listOfSavedRecordsPart,
                    listOfCheckedItems,
                    context
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val activeBottomItem: String = getActiveBottomItem(backStackEntry)

                if (activeBottomItem == BottomItems.Box.name) {
                    val dataForBox = BoxQRcode(
                        packaging.value,
                        article.value,
                        index.value,
                        quantityInBox.value,
                        batchNumber.value,
                        customerArticle.value
                    )
                    saveToSharedPreferences(context, dataBox = dataForBox)

                } else if (activeBottomItem == BottomItems.Part.name) {
                    val dataForPart = PartQRcode(
                        article.value,
                        index.value,
                        customerArticle.value,
                        if (isHWpresent.value) HWversionPART.value else "no",
                        if (isSWpresent.value) SWversionPART.value else "no",
                        serialNumberPART.value,
                        isHWpresent.value,
                        isSWpresent.value
                    )
                    saveToSharedPreferences(context, dataPart = dataForPart)
                }
                makeToast(context, "Save")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = null
                )
            }
//                    IconButton(onClick = { makeToast(context, "Save") }) {
//                        Icon(painter = icon2, contentDescription = null)
//                    }
        },
    )
}
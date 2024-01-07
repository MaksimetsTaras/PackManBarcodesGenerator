package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barcodeGenerator.BoxQRcode
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.screens.BottomItems
import com.example.packmanbarcodesgenerator.screens.makeToast
import com.google.gson.Gson
import mySharedPreferences.mySharedPreferences

@SuppressLint("RememberReturnType")
@Composable
fun CustomAlertDialog(
    state: MutableState<Boolean>,
    activeBottomItem: String,
    fieldsForPart: Map<String, MutableState<String>>,
    fieldsForPartHWandSW: Map<String, MutableState<Boolean>>,
    fieldsForBox: Map<String, MutableState<String>>,
    listOfRecords: List<Any>,
    listOfCheckedItems: SnapshotStateList<Int>,
    context: Context
) {

    val mySharedPreferences = mySharedPreferences(context)

    if (state.value) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { state.value = false },
            title = null,
            text = null,
            buttons = {
                Column(verticalArrangement = Arrangement.SpaceBetween) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Попередньо збережені: " + listOfRecords.count() + "шт.",
                            fontSize = 20.sp
                        )
                    }

                    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Column {
                            var neededList: List<Any> = listOfRecords.filterIsInstance<BoxQRcode>()
                            if (activeBottomItem == BottomItems.Box.name) {
                                neededList = listOfRecords.filterIsInstance<BoxQRcode>()
                            } else if (activeBottomItem == BottomItems.Part.name) {
                                neededList = listOfRecords.filterIsInstance<PartQRcode>()
                            }

                            CustomListView(
                                context = LocalContext.current,
                                neededList,
                                listOfCheckedItems
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                if (listOfCheckedItems.count() != 1) {
                                    listOfCheckedItems.clear()
                                    makeToast(context, "Оберіть ОДИН елемент")
                                } else {

                                    val neededList: Any
                                    if (activeBottomItem == BottomItems.Box.name) {
                                        neededList = listOfRecords.filterIsInstance<BoxQRcode>()

                                        val chosenRecord = neededList[listOfCheckedItems[0]]
                                        fieldsForBox["article"]?.value = chosenRecord.article
                                        fieldsForBox["index"]?.value = chosenRecord.index
                                        fieldsForBox["packaging"]?.value = chosenRecord.packaging
                                        fieldsForBox["quantityInBox"]?.value =
                                            chosenRecord.quantityInBox
                                        fieldsForBox["batchNumber"]?.value =
                                            chosenRecord.batchNumber
                                        fieldsForBox["customerArticle"]?.value =
                                            chosenRecord.customerArticle


                                    } else if (activeBottomItem == BottomItems.Part.name) {
                                        val neededList: List<PartQRcode> =
                                            listOfRecords.filterIsInstance<PartQRcode>()

                                        val chosenRecord = neededList[listOfCheckedItems[0]]
                                        fieldsForPart["article"]?.value = chosenRecord.article
                                        fieldsForPart["index"]?.value = chosenRecord.index
                                        fieldsForPart["customerArticle"]?.value =
                                            chosenRecord.customerArticle
                                        fieldsForPart["HWversion"]?.value = chosenRecord.HWversion
                                        fieldsForPart["SWversion"]?.value = chosenRecord.SWversion
                                        fieldsForPart["serialNumber"]?.value =
                                            chosenRecord.serialNumber
                                        fieldsForPartHWandSW["isHWpresent"]?.value =
                                            chosenRecord.isHWpresent
                                        fieldsForPartHWandSW["isSWpresent"]?.value =
                                            chosenRecord.isSWpresent
                                    }

                                    state.value = false
                                }
                            }
                        ) {
                            Text("Обрати")
                        }

                        Button(
                            onClick = {
//                                state.value = false
                                if (listOfCheckedItems.isEmpty()) {
                                    listOfCheckedItems.clear()
                                    makeToast(context, "Оберіть хоча б ОДИН елемент")
                                } else {

                                    val gson = Gson()

                                    for (element in listOfCheckedItems) {
                                        val currentRecordToDelete = listOfRecords[element]
                                        val recordInJson = gson.toJson(currentRecordToDelete)

                                        //видалити з sharedPreferences
                                        mySharedPreferences.deleteRecordByValue(
                                            recordInJson
                                        )
                                        //оновити список
                                        state.value = false
                                        state.value = true

//                                        if (activeBottomItem == BottomItems.Box.name) {
//
//
//
////                                            fieldsForBox = mySharedPreferences.getAllRecords_WhereKeyStartsWith(BottomItems.Box.name)
//                                        } else if (activeBottomItem == BottomItems.Part.name) {
//
//                                        }


                                    }
                                }
                            }
                        ) {
                            Text("Видалити")
                        }
                    }
                }
            }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = false)
@Composable
fun CustomAlertDialogPreview() {

    val partItem = PartQRcode(
        "12181454", "03", "254789361R", "23.1", "6.2", "9428812345678",
        isHWpresent = true,
        isSWpresent = true
    )

    val state: MutableState<Boolean> = mutableStateOf(true)
    val activeBottomItem: String = "Box"
    val fieldsForPart: Map<String, MutableState<String>> = mapOf(
        "article" to mutableStateOf(""),
        "index" to mutableStateOf(""),
        "customerArticle" to mutableStateOf(""),
        "HWversionPART" to mutableStateOf(""),
        "SWversionPART" to mutableStateOf(""),
        "serialNumberPART" to mutableStateOf("")
    )
    val fieldsForPartHWandSW: Map<String, MutableState<Boolean>> = mapOf(
        "isHWpresent" to mutableStateOf(false),
        "isSWpresent" to mutableStateOf(false)
    )

    val fieldsForBox: Map<String, MutableState<String>> = mapOf(
        "article" to mutableStateOf(""),
        "index" to mutableStateOf(""),
        "customerArticle" to mutableStateOf(""),
        "packaging" to mutableStateOf(""),
        "quantityInBox" to mutableStateOf(""),
        "batchNumber" to mutableStateOf("")
    )
    val listOfRecords: List<Any> = listOf { partItem }


    val listOfCheckedItems: SnapshotStateList<Int> = SnapshotStateList()
    val context: Context = LocalContext.current

    CustomAlertDialog(
        state,
        activeBottomItem,
        fieldsForPart,
        fieldsForPartHWandSW,
        fieldsForBox,
        listOfRecords,
        listOfCheckedItems,
        context
    )
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    Text(text = "Hello Compose!")
//}

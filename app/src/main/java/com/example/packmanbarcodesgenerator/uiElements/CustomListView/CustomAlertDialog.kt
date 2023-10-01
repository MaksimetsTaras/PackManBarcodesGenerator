package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import barcodeGenerator.BoxQRcode
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.screens.BottomItems
import com.example.packmanbarcodesgenerator.screens.makeToast

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

    if (state.value) {
        AlertDialog(
            onDismissRequest = { state.value = false },
            title = { Text(text = "Попередньо збережені...") },
            text = {
                Column() {

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
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
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
                                    fieldsForBox["batchNumber"]?.value = chosenRecord.batchNumber
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
                                    fieldsForPart["serialNumber"]?.value = chosenRecord.serialNumber
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
                        onClick = { state.value = false }
                    ) {
                        Text("Видалити")
                    }
                }
            }
        )
    }
}
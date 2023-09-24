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
import com.example.packmanbarcodesgenerator.makeToast

@SuppressLint("RememberReturnType")
@Composable
fun CustomAlertDialog(
    state: MutableState<Boolean>,
    listOfRecords: List<RecordDataClass>,
    listOfCheckedItems: SnapshotStateList<Int>,
    recordSaved: MutableState<RecordDataClass>,
    context: Context
) {

    if (state.value) {
        AlertDialog(
            onDismissRequest = { state.value = false },
            title = { Text(text = "Попередньо збережені...") },
            text = {
                Column() {
                    CustomListView(
                        context = LocalContext.current,
                        listOfRecords,
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
                                recordSaved.value = listOfRecords[listOfCheckedItems[0]]
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
package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.annotation.SuppressLint
import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.packmanbarcodesgenerator.R

@SuppressLint("RememberReturnType")
@Composable
fun CustomAlertDialog(
    state: MutableState<Boolean>,
    listOfRecords: List<RecordDataClass>,
    activeBottomItem: String,
    listOfCheckedItems: SnapshotStateList<Int>
) {
//    val openDialog = remember { mutableStateOf(true) }
//    var text by remember { mutableStateOf("") }

    if (state.value) {
        AlertDialog(
            onDismissRequest = { state.value = false },
            title = { Text(text = "Попередньо збережені...") },
            text = {
                Column() {
                    customListView(
                        context = LocalContext.current,
                        activeBottomItem,
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
                        onClick = { state.value = false }
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
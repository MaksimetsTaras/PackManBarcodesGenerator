package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import barcodeGenerator.BoxQRcode
import barcodeGenerator.PartQRcode

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomListView(
    context: Context,
    listOfRecords: List<Any>,
    listOfCheckedItems: SnapshotStateList<Int>
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    LazyColumn(
        modifier = Modifier
            .height(
                if (screenHeight > 400.dp) {
                    300.dp
                } else {
                    screenHeight - 200.dp
                }
            )
            .background(Color.Transparent)
            .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(0.dp)),
    ) {
        itemsIndexed(listOfRecords) { index, item ->
            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
//                    Toast.makeText(
//                        context,
//                        listOfRecords[index].article + " selected..",
//                        Toast.LENGTH_SHORT
//                    ).show()
                },
                modifier = Modifier.padding(8.dp),
                border = BorderStroke(0.dp, Color.Transparent),
                backgroundColor = Color.Transparent,
                elevation = 6.dp
            )
            {
                val currentTypeofList = listOfRecords[0]::class.simpleName

                if (currentTypeofList == BoxQRcode::class.simpleName) {
                    val neededList: List<BoxQRcode> =
                        listOfRecords.filterIsInstance<BoxQRcode>()

                    BoxItemForListView(neededList[index], index, listOfCheckedItems)
                } else if (currentTypeofList == PartQRcode::class.simpleName) {
                    val neededList: List<PartQRcode> =
                        listOfRecords.filterIsInstance<PartQRcode>()

                    PartItemForListView(neededList[index], index, listOfCheckedItems)
                }
            }
        }
    }
}
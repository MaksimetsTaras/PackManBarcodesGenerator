package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomListView(
    context: Context,
    listOfRecords: List<RecordDataClass>,
    listOfCheckedItems: SnapshotStateList<Int>
) {
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .background(Color.Transparent)
            .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(0.dp)),
    ) {
        itemsIndexed(listOfRecords) { index, item ->
            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        listOfRecords[index].article + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(8.dp),
                border = BorderStroke(0.dp, Color.Transparent),
                backgroundColor = Color.Transparent,
                elevation = 6.dp
            )
            {
                BoxItemForListView(listOfRecords[index], index, listOfCheckedItems)
            }
        }
    }
}
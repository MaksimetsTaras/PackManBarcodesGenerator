package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import ListViewElementModel
import android.content.Context
import android.widget.ListView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.packmanbarcodesgenerator.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun customListView(
    context: Context,
    activeBottomItem: String,
    listOfRecords: List<RecordDataClass>,
    listOfCheckedItems: SnapshotStateList<Int>
) {
    // on below line we are creating a lazy column for displaying a list view.
    LazyColumn(modifier = Modifier.height(300.dp).background(Color(R.color.teal_700))) {
        // on below line we are setting data for each item of our listview.
        itemsIndexed(listOfRecords) { index, item ->
            // on below line we are creating a card for our list view item.
            Card(
                // inside our grid view on below line
                // we are adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        listOfRecords[index].article + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),

                // on below line we are adding elevation for the card.
                elevation = 6.dp
            )
            {
                BoxItemForListView(listOfRecords[index], index, listOfCheckedItems)
            }
        }
    }
}
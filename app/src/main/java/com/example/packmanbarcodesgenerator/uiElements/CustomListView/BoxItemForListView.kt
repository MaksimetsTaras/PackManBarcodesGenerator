package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import ListViewElementModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BoxItemForListView(
    listViewElementModel: ListViewElementModel,
    indexOfElement: Int,
    listOfCheckedItems: SnapshotStateList<Int>
) {
    val checkedState = remember { mutableStateOf(false) }

    // on below line we are creating a row for our list view item.
    Row(
        // for our row we are adding modifier to set padding from all sides.
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it

                if (it == false) {
                    listOfCheckedItems.remove(indexOfElement)
                } else {
                    listOfCheckedItems.add(indexOfElement)
                }
            },
            modifier = Modifier.padding(1.dp).size(30.dp)
        )

        Text(
            // inside the text on below line we are setting text as the language name
            // from our modal class.
            text = listViewElementModel.languageName,

            // on below line we are adding padding for our text from all sides.
            modifier = Modifier.padding(1.dp),

            // on below line we are adding color for our text
            color = Color.Black, textAlign = TextAlign.Center
        )

//
//        // on below line inside row we are adding spacer
//        Spacer(modifier = Modifier.width(5.dp))
//
//        // on below line we are adding image to display the image.
//        Image(
//            // on below line we are specifying the drawable image for our image.
//            painter = painterResource(id = listViewElementModel.languageImg),
//
//            // on below line we are specifying content description for our image
//            contentDescription = "Javascript",
//
//            // on below line we are setting height and width for our image.
//            modifier = Modifier
//                .height(60.dp)
//                .width(60.dp)
//                .padding(5.dp)
//        )
//
//        // on below line we are adding spacer between image and a text
//        Spacer(modifier = Modifier.width(5.dp))
//
//        // on the below line we are creating a text.
//        Text(
//            // inside the text on below line we are setting text as the language name
//            // from our modal class.
//            text = listViewElementModel.languageName,
//
//            // on below line we are adding padding for our text from all sides.
//            modifier = Modifier.padding(4.dp),
//
//            // on below line we are adding color for our text
//            color = Color.Black, textAlign = TextAlign.Center
//        )
    }

}
package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import ListViewElementModel
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.packmanbarcodesgenerator.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun customListView(
    context: Context,
    activeBottomItem: String,
    listOfCheckedItems: SnapshotStateList<Int>
) {
    // on below line we are creating and initializing our array list
    lateinit var courseList: List<ListViewElementModel>
    courseList = ArrayList<ListViewElementModel>()

    // on below line we are adding data to our list.
    courseList = courseList + ListViewElementModel("Android", R.drawable.box)
    courseList = courseList + ListViewElementModel("JavaScript", R.drawable.box)
    courseList = courseList + ListViewElementModel("Python", R.drawable.box)
    courseList = courseList + ListViewElementModel("C++", R.drawable.box)
    courseList = courseList + ListViewElementModel("C#", R.drawable.box)
    courseList = courseList + ListViewElementModel("Java", R.drawable.box)
    courseList = courseList + ListViewElementModel("Node Js", R.drawable.box)
    courseList = courseList + ListViewElementModel("C#", R.drawable.box)
    courseList = courseList + ListViewElementModel("Java", R.drawable.box)
    courseList = courseList + ListViewElementModel("Node Js", R.drawable.box)

    // on below line we are creating a lazy column for displaying a list view.
    LazyColumn(modifier = Modifier.height(200.dp)) {
        // on below line we are setting data for each item of our listview.
        itemsIndexed(courseList) { index, item ->
            // on below line we are creating a card for our list view item.
            Card(
                // inside our grid view on below line
                // we are adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        courseList[index].languageName + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),

                // on below line we are adding elevation for the card.
                elevation = 6.dp
            )
            {
                BoxItemForListView(courseList[index],index , listOfCheckedItems)
                // on below line we are creating a row for our list view item.
//                Row(
//                    // for our row we are adding modifier to set padding from all sides.
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//                ) {
//                    // on below line inside row we are adding spacer
//                    Spacer(modifier = Modifier.width(5.dp))
//
//                    // on below line we are adding image to display the image.
//                    Image(
//                        // on below line we are specifying the drawable image for our image.
//                        painter = painterResource(id = courseList[index].languageImg),
//
//                        // on below line we are specifying content description for our image
//                        contentDescription = "Javascript",
//
//                        // on below line we are setting height and width for our image.
//                        modifier = Modifier
//                            .height(60.dp)
//                            .width(60.dp)
//                            .padding(5.dp)
//                    )
//
//                    // on below line we are adding spacer between image and a text
//                    Spacer(modifier = Modifier.width(5.dp))
//
//                    // on the below line we are creating a text.
//                    Text(
//                        // inside the text on below line we are setting text as the language name
//                        // from our modal class.
//                        text = courseList[index].languageName,
//
//                        // on below line we are adding padding for our text from all sides.
//                        modifier = Modifier.padding(4.dp),
//
//                        // on below line we are adding color for our text
//                        color = Color.Black, textAlign = TextAlign.Center
//                    )
//                }
            }
        }
    }
}
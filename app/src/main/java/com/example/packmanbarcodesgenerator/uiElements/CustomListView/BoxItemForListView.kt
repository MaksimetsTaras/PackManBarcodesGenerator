package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barcodeGenerator.BoxQRcode


@Composable
fun BoxItemForListView(
    boxItem: BoxQRcode,
    indexOfElement: Int = 0,
    listOfCheckedItems: SnapshotStateList<Int> = SnapshotStateList()
) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it

                    if (!it) {
                        listOfCheckedItems.remove(indexOfElement)
                    } else {
                        listOfCheckedItems.add(indexOfElement)
                    }
                },
                modifier = Modifier
                    .padding(1.dp)
                    .size(30.dp)
            )
        }

        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "key - " + boxItem.article + "-" + boxItem.index,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    LabelAndText(labelValue = "customer", textValue = boxItem.customerArticle)

                    LabelAndText(labelValue = "HU", textValue = boxItem.packaging)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        LabelAndText(labelValue = "batch", textValue = boxItem.batchNumber)

                        Spacer(modifier = Modifier.width(20.dp))

                        LabelAndText(labelValue = "Qty", textValue = boxItem.quantityInBox)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BoxItemPreview() {
    val boxItem = BoxQRcode(
        "453940087", "10544017", "00", "450", "720716", "A1749055601"
    )

    val snap: SnapshotStateList<Int> = SnapshotStateList<Int>()

    BoxItemForListView(boxItem, 1, snap)
}
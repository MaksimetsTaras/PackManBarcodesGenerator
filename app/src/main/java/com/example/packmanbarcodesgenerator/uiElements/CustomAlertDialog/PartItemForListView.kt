package com.example.packmanbarcodesgenerator.uiElements.CustomAlertDialog

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
import barcodeGenerator.PartQRcode

@Composable
fun PartItemForListView(
    partItem: PartQRcode,
    indexOfElement: Int,
    listOfCheckedItems: SnapshotStateList<Int>
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
                    text = partItem.article + "-" + partItem.index,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                LabelAndText(labelValue = "customer", textValue = partItem.customerArticle)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                if (partItem.HWversion.isNotEmpty()) {
                    LabelAndText(labelValue = "HW", textValue = partItem.HWversion)
                }

                Spacer(modifier = Modifier.width(20.dp))

                if (partItem.SWversion.isNotEmpty()) {
                    LabelAndText(labelValue = "SW", textValue = partItem.SWversion)
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PartItemPreview() {
    val partItem = PartQRcode(
        "12181454", "03", "254789361R", "23.1", "6.2", "9428812345678",
        isHWpresent = true,
        isSWpresent = true
    )

    val snap: SnapshotStateList<Int> = SnapshotStateList<Int>()

    PartItemForListView(partItem, 1, snap)
}
package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.packmanbarcodesgenerator.R

@Composable
fun BoxItemForListView(
    record: RecordDataClass,
    indexOfElement: Int,
    listOfCheckedItems: SnapshotStateList<Int>
) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
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
                modifier = Modifier
                    .padding(1.dp)
                    .size(30.dp)
            )
        }

        Column {
            Row(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "key - " + record.article + "-" + record.index,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = record.customerArticle)

                if (record.HWversion != null) {
                    Text(text = record.HWversion)
                }

                if (record.SWversion != null) {
                    Text(text = record.SWversion)
                }
            }
        }
    }
}
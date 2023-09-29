package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LabelAndText(labelValue: String, textValue: String) {
    Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
        Text(text = labelValue)
        Text(text = ": ")
        Text(text = textValue, fontWeight = FontWeight.Bold)
    }

}
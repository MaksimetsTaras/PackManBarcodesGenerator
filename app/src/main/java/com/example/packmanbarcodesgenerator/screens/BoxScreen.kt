package com.example.packmanbarcodesgenerator.screens

import BarcodeGenerator.BarcodeGenerator
import BarcodeGenerator.BoxQRcode
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = false)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen() {

    var packaging = remember { mutableStateOf(TextFieldValue("46")) }
    var article = remember { mutableStateOf(TextFieldValue("10541451")) }
    var index = remember { mutableStateOf(TextFieldValue("07")) }
    var quantityInBox = remember { mutableStateOf(TextFieldValue("100")) }
    var batchNumber = remember { mutableStateOf(TextFieldValue("46")) }
    var customerArticle = remember { mutableStateOf(TextFieldValue("O3854712")) }

    Scaffold() {
        Column() {
            TextField(
                article.value,
                onValueChange = { newText -> article.value = newText },
                label = { Text(text = "article") },
                placeholder = { Text(text = "артикль") }
            )

            TextField(
                index.value,
                onValueChange = { newText -> index.value = newText },
                label = { Text(text = "index") },
                placeholder = { Text(text = "індекс") }
            )

            val boxInfoForQRcode = BoxQRcode(
                article = article.value.toString(),
                index = index.value.toString(),
                quantityInBox = quantityInBox.value.toString(),
                customerArticle = customerArticle.value.toString()
            )

            val barcodeGenerator = BarcodeGenerator()
            val boxQRcode: Painter = barcodeGenerator.createBoxQRcode(boxInfoForQRcode)

            Image(
                painter = boxQRcode,
                contentDescription = "DEV Communit Code",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(135.dp),
            )
        }
    }
}
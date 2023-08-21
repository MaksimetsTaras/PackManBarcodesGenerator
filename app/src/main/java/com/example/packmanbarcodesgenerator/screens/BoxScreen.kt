package com.example.packmanbarcodesgenerator.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.BoxQRcode
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons
import nextNumberTool.NextNumberTool

@Preview(showBackground = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen() {

    val packaging = remember { mutableStateOf(TextFieldValue("453940087")) }
    val article = remember { mutableStateOf(TextFieldValue("10541451")) }
    val index = remember { mutableStateOf(TextFieldValue("07")) }
    val quantityInBox = remember { mutableStateOf(TextFieldValue("100")) }
    val batchNumber = remember { mutableStateOf(TextFieldValue("720716")) }
    val customerArticle = remember { mutableStateOf(TextFieldValue("O3854712")) }

    val qrCode = remember {
        mutableStateOf(
            ImageBitmap(
                width = 300,
                height = 300,
                config = ImageBitmapConfig.Argb8888,
                hasAlpha = true,
                colorSpace = ColorSpaces.Srgb
            )
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                bitmap = qrCode.value,
                contentDescription = "QR code",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally),
            )

            TextField_withButtons(element = article, modifier = Modifier, labelValue = "артикль")

            TextField_withButtons(element = index, modifier = Modifier, labelValue = "індекс")

            TextField_withButtons(
                element = quantityInBox, modifier = Modifier, labelValue = "Кількість в ящику"
            )

            TextField_withButtons(
                element = customerArticle, modifier = Modifier, labelValue = "Артикль замовника"
            )

            TextField_withButtons(
                element = packaging, modifier = Modifier, labelValue = "Пакування"
            )

            TextField_withButtons(
                element = batchNumber, modifier = Modifier, labelValue = "Бетч"
            )

            Button(
                onClick = {
                    qrCode.value = generateQRcode(
                        packaging.value.text,
                        article.value.text,
                        index.value.text,
                        quantityInBox.value.text,
                        batchNumber.value.text,
                        customerArticle.value.text
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {
                Text(text = "Згенерувати", color = Color.White)
            }
        }
    }
}

fun generateQRcode(
    packaging: String,
    article: String,
    index: String,
    quantityInBox: String,
    batch: String,
    customerArticle: String
): ImageBitmap {
    val boxInfoForQRcode = BoxQRcode(
        packaging = packaging,
        article = article,
        index = index,
        quantityInBox = quantityInBox,
        batchNumber = batch,
        customerArticle = customerArticle
    )

    val barcodeGenerator = BarcodeGenerator()

    return barcodeGenerator.createBoxQRcode(boxInfoForQRcode)
}

fun incrementValue(valueToIncrement: String): String {
    val incrementTool = NextNumberTool()

    return incrementTool.incrementValue(valueToIncrement)
}

fun decrementValue(valueToDecrement: String): String {
    val incrementTool = NextNumberTool()

    return incrementTool.decrementValue(valueToDecrement)
}
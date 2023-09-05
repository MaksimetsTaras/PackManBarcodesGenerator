package com.example.packmanbarcodesgenerator.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.packmanbarcodesgenerator.TypesOfInput
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen(setFabOnClick: (() -> Unit) -> Unit,
              article: MutableState<TextFieldValue>,
              index: MutableState<TextFieldValue>,
              packaging: MutableState<TextFieldValue>,
              quantityInBox: MutableState<TextFieldValue>,
              batchNumber: MutableState<TextFieldValue>,
              customerArticle: MutableState<TextFieldValue>
) {

//    val packaging = remember { mutableStateOf(TextFieldValue("453940087")) }
//    val article = remember { mutableStateOf(TextFieldValue(article.value.toString())) }
//    val index = remember { mutableStateOf(TextFieldValue("00")) }
//    val quantityInBox = remember { mutableStateOf(TextFieldValue("10")) }
//    val batchNumber = remember { mutableStateOf(TextFieldValue("720716")) }
//    val customerArticle = remember { mutableStateOf(TextFieldValue("A1749055601")) }

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

    LaunchedEffect(Unit) {
        setFabOnClick {  qrCode.value = generate_BoxQRcode(
            packaging.value.text,
            article.value.text,
            index.value.text,
            quantityInBox.value.text,
            batchNumber.value.text,
            customerArticle.value.text
        ) }
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
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally),
            )

            TextField_withButtons(element = article, modifier = Modifier, labelValue = "артикль", TypesOfInput.integer)

            TextField_withButtons(element = index, modifier = Modifier, labelValue = "індекс", TypesOfInput.integer)

            TextField_withButtons(
                element = quantityInBox, modifier = Modifier, labelValue = "Кількість в ящику", TypesOfInput.integer
            )

            TextField_withButtons(
                element = customerArticle, modifier = Modifier, labelValue = "Артикль замовника", TypesOfInput.text
            )

            TextField_withButtons(
                element = packaging, modifier = Modifier, labelValue = "Пакування", TypesOfInput.integer
            )

            TextField_withButtons(element = batchNumber, modifier = Modifier, labelValue = "Бетч", TypesOfInput.text)

        }
    }
}

fun generate_BoxQRcode(
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
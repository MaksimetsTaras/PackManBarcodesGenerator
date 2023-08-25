package com.example.packmanbarcodesgenerator.screens

import TextAndSwitch
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartScreen() {

    val article = remember { mutableStateOf(TextFieldValue("10541450")) }
    val index = remember { mutableStateOf(TextFieldValue("02")) }
    val customerNumber = remember { mutableStateOf(TextFieldValue("2527355354R")) }
    val HWversion = remember { mutableStateOf(TextFieldValue("21.1")) }
    val SWversion = remember { mutableStateOf(TextFieldValue("8.1")) }
    val serialNumber = remember { mutableStateOf(TextFieldValue("94288WGI00081")) }

    val switchCheckedStateHWversion = remember { mutableStateOf(false) }
    val switchCheckedStateSWversion = remember { mutableStateOf(false) }

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextAndSwitch(
                    "HW",
                    modifier = Modifier.weight(1f),
                    element = switchCheckedStateHWversion
                )
                TextAndSwitch(
                    "SW",
                    modifier = Modifier.weight(1f),
                    element = switchCheckedStateSWversion
                )
            }


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
                element = customerNumber, modifier = Modifier, labelValue = "Артикль замовника"
            )

            TextField_withButtons(
                element = HWversion,
                modifier = Modifier,
                labelValue = "HW версія"
            )

            TextField_withButtons(
                element = SWversion,
                modifier = Modifier,
                labelValue = "SW версія"
            )

            TextField_withButtons(
                element = serialNumber,
                modifier = Modifier,
                labelValue = "94288WGI00081"
            )

            Button(
                onClick = {
                    qrCode.value = generate_PartQRcode(
                        article.value.text,
                        index.value.text,
                        customerNumber.value.text,
                        HWversion.value.text,
                        SWversion.value.text,
                        serialNumber.value.text
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {
                androidx.compose.material3.Text(text = "Згенерувати", color = Color.White)
            }
        }
    }
}

fun generate_PartQRcode(
    article: String,
    index: String,
    customerNumber: String,
    HWversion: String,
    SWversion: String,
    serialNumber: String
): ImageBitmap {
    val partInfoForQRcode = PartQRcode(
        article = article,
        index = index,
        customerNumber = customerNumber,
        HWversion = HWversion,
        SWversion = SWversion,
        serialNumber = serialNumber
    )

    val barcodeGenerator = BarcodeGenerator()

    return barcodeGenerator.createPartQRcode(partInfoForQRcode)
}
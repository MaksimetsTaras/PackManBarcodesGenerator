package com.example.packmanbarcodesgenerator.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.BoxQRcode
import com.example.packmanbarcodesgenerator.R
import com.example.packmanbarcodesgenerator.TypesOfInput
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen(
    setFabOnClick: (() -> Unit) -> Unit,
    article: MutableState<String>,
    index: MutableState<String>,
    packaging: MutableState<String>,
    quantityInBox: MutableState<String>,
    batchNumber: MutableState<String>,
    customerArticle: MutableState<String>
) {

    val configuration = LocalConfiguration.current

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
        setFabOnClick {
            qrCode.value = generateBoxQRcode(
                packaging.value,
                article.value,
                index.value,
                quantityInBox.value,
                batchNumber.value,
                customerArticle.value
            )
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                BoxScreenLandscape(
                    qrCode, article, index, packaging, quantityInBox, batchNumber, customerArticle
                )
            }

            Configuration.ORIENTATION_PORTRAIT -> {
                BoxScreenPortrait(
                    qrCode, article, index, packaging, quantityInBox, batchNumber, customerArticle
                )
            }

            Configuration.ORIENTATION_SQUARE -> {}
            Configuration.ORIENTATION_UNDEFINED -> {}
        }
    }
}


@Composable
fun BoxScreenPortrait(
    qrCode: MutableState<ImageBitmap>,
    article: MutableState<String>,
    index: MutableState<String>,
    packaging: MutableState<String>,
    quantityInBox: MutableState<String>,
    batchNumber: MutableState<String>,
    customerArticle: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = qrCode.value,
                contentDescription = "QR code",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {

                TextField_withButtons(
                    element = article,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.article),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = index,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.index),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = quantityInBox,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.qty_in_box),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = customerArticle,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.customer_number),
                    TypesOfInput.text
                )

                TextField_withButtons(
                    element = packaging,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.packaging),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = batchNumber,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.batch),
                    TypesOfInput.text
                )
            }
        }
    }
}

@Composable
fun BoxScreenLandscape(
    qrCode: MutableState<ImageBitmap>,
    article: MutableState<String>,
    index: MutableState<String>,
    packaging: MutableState<String>,
    quantityInBox: MutableState<String>,
    batchNumber: MutableState<String>,
    customerArticle: MutableState<String>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    Image(
                        bitmap = qrCode.value,
                        contentDescription = "QR code",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(150.dp)
                            .fillMaxWidth()
                    )
                }

            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                TextField_withButtons(
                    element = article,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.article),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = index,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.index),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = quantityInBox,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.qty_in_box),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = customerArticle,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.customer_number),
                    TypesOfInput.text
                )

                TextField_withButtons(
                    element = packaging,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.packaging),
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = batchNumber,
                    modifier = Modifier,
                    labelValue = stringResource(R.string.batch),
                    TypesOfInput.text
                )

            }

        }
    }
}

fun generateBoxQRcode(
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

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = false)
@Composable
fun BoxScreenPreview() {
//    Text(text = "Hello Compose!")

    val (_, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null) }
    val article: MutableState<String> = mutableStateOf("10544017")
    val index: MutableState<String> = mutableStateOf("03")
    val packaging: MutableState<String> = mutableStateOf("453940087")
    val quantityInBox: MutableState<String> = mutableStateOf("50")
    val batchNumber: MutableState<String> = mutableStateOf("720716")
    val customerArticle: MutableState<String> = mutableStateOf("A1749055601")

    BoxScreen(
        setFabOnClick = setFabOnClick,
        article = article,
        index = index,
        packaging = packaging,
        quantityInBox = quantityInBox,
        batchNumber = batchNumber,
        customerArticle = customerArticle
    )

}
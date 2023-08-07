package com.example.packmanbarcodesgenerator.screens

import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.BoxQRcode
import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.packmanbarcodesgenerator.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = false)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen() {



    var packaging = remember { mutableStateOf(TextFieldValue("46")) }
    val article = remember { mutableStateOf(TextFieldValue("10541451")) }
    val index = remember { mutableStateOf(TextFieldValue("07")) }
    val quantityInBox = remember { mutableStateOf(TextFieldValue("100")) }
    var batchNumber = remember { mutableStateOf(TextFieldValue("46")) }
    val customerArticle = remember { mutableStateOf(TextFieldValue("O3854712")) }

    val qrCode: ImageBitmap = remember {generateQRcode().asImageBitmap()}

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                bitmap = qrCode,
                contentDescription = "QR code",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(135.dp)
                    .align(Alignment.CenterHorizontally),
            )

            TextField_withButtons(element = article, modifier = Modifier, labelValue = "артикль")

            TextField_withButtons(element = index, modifier = Modifier, labelValue = "індекс")

            TextField_withButtons(
                element = quantityInBox,
                modifier = Modifier,
                labelValue = "кількість в ящику"
            )

            TextField_withButtons(
                element = customerArticle,
                modifier = Modifier,
                labelValue = "Артикль замовника"
            )

//            @Composable
//            fun generateQRcode(): BitmapPainter {
//                val boxInfoForQRcode = BoxQRcode(
//                    article = article.value.text.toString(),
//                    index = index.value.text.toString(),
//                    quantityInBox = quantityInBox.value.text.toString(),
//                    customerArticle = customerArticle.value.text.toString()
//                )
//
//                val boxQRcode: BitmapPainter = barcodeGenerator.createBoxQRcode(boxInfoForQRcode)
//
//                return boxQRcode
//            }

            Button(
                onClick = {
                    generateQRcode()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {
                Text(text = "Згенерувати", color = Color.White)
            }
        }
    }

//    @Composable
//    fun generateQRcode(): BitmapPainter {
//        val boxInfoForQRcode = BoxQRcode(
//            article = article.value.text.toString(),
//            index = index.value.text.toString(),
//            quantityInBox = quantityInBox.value.text.toString(),
//            customerArticle = customerArticle.value.text.toString()
//        )
//
//        val boxQRcode: BitmapPainter = barcodeGenerator.createBoxQRcode(boxInfoForQRcode)
//
//        return boxQRcode
//    }
}


fun generateQRcode(): Bitmap {
//    val boxInfoForQRcode = BoxQRcode(
//        article = article.value.text.toString(),
//        index = index.value.text.toString(),
//        quantityInBox = quantityInBox.value.text.toString(),
//        customerArticle = customerArticle.value.text.toString()
//    )

    val boxInfoForQRcode = BoxQRcode(
        article = "article",
        index = "index",
        quantityInBox = "quantityInBox",
        customerArticle = "customerArticle"
    )

    val barcodeGenerator = BarcodeGenerator()
    val boxQRcode: Bitmap = barcodeGenerator.createBoxQRcode(boxInfoForQRcode)


    return boxQRcode
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField_withButtons(
    element: MutableState<TextFieldValue>,
    modifier: Modifier,
    labelValue: String,
) {
    val scope = rememberCoroutineScope()

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            element.value,
            onValueChange = { text -> element.value = text },
            textStyle = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                background = Color.Transparent
            ),
            label = {
                Text(
                    text = labelValue,
                    textAlign = TextAlign.Left,
                    modifier = Modifier,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = modifier
                .onFocusChanged {
                    if (it.hasFocus) {
                        scope.launch {
                            delay(10)
                            val text = element.value.text
                            element.value = element.value.copy(
                                selection = TextRange(0, text.length)
                            )
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Row() {
            Image(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Minus",
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .width(80.dp)
                    .clickable {
                        element.value = TextFieldValue("Taras")
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Plus",
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .width(80.dp)
                    .clickable {

                    }
            )
        }
    }
}
package com.example.packmanbarcodesgenerator.screens

import TextAndSwitch
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.makeToast
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartScreen(setFabOnClick: (() -> Unit) -> Unit) {

    val article = remember { mutableStateOf(TextFieldValue("10544017")) }
    val index = remember { mutableStateOf(TextFieldValue("00")) }
    val customerNumber = remember { mutableStateOf(TextFieldValue("A1749055601")) }
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

    LaunchedEffect(Unit) {
        setFabOnClick {
            qrCode.value = generate_PartQRcode(
                article.value.text,
                index.value.text,
                customerNumber.value.text,
                HWversion.value.text,
                SWversion.value.text,
                serialNumber.value.text,
                switchCheckedStateHWversion.value,
                switchCheckedStateSWversion.value,
            )
        }
    }




    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp, 10.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextAndSwitch(
                    "HW", modifier = Modifier.weight(1f), element = switchCheckedStateHWversion
                )
                Spacer(modifier = Modifier.width(20.dp))

                TextAndSwitch(
                    "SW", modifier = Modifier.weight(1f), element = switchCheckedStateSWversion
                )
            }

            Text(
                text = multipleColorsInText(
                    switchCheckedStateSWversion,
                    switchCheckedStateHWversion
                ),
                Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

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

                TextField_withButtons(
                    element = article,
                    modifier = Modifier,
                    labelValue = "артикль"
                )

                TextField_withButtons(
                    element = index,
                    modifier = Modifier,
                    labelValue = "індекс"
                )

                TextField_withButtons(
                    element = customerNumber,
                    modifier = Modifier,
                    labelValue = "Артикль замовника"
                )

                if (switchCheckedStateHWversion.value) {
                    TextField_withButtons(
                        element = HWversion,
                        modifier = Modifier,
                        labelValue = "HW версія"
                    )
                }

                if (switchCheckedStateSWversion.value) {
                    TextField_withButtons(
                        element = SWversion,
                        modifier = Modifier,
                        labelValue = "SW версія"
                    )
                }

                TextField_withButtons(
                    element = serialNumber,
                    modifier = Modifier,
                    labelValue = "94288WGI00081"
                )

//                Button(
//                    onClick = {
//                        qrCode.value = generate_PartQRcode(
//                            article.value.text,
//                            index.value.text,
//                            customerNumber.value.text,
//                            HWversion.value.text,
//                            SWversion.value.text,
//                            serialNumber.value.text,
//                            switchCheckedStateHWversion.value,
//                            switchCheckedStateSWversion.value,
//                        )
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(5.dp),
//                ) {
//                    androidx.compose.material3.Text(text = "Згенерувати", color = Color.White)
//                }
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
    serialNumber: String,
    isHWpresent: Boolean,
    isSWpresent: Boolean
): ImageBitmap {
    val partInfoForQRcode = PartQRcode(
        article = article,
        index = index,
        customerNumber = customerNumber,
        HWversion = HWversion,
        SWversion = SWversion,
        serialNumber = serialNumber,
        isHWpresent = isHWpresent,
        isSWpresent = isSWpresent,
    )

    val barcodeGenerator = BarcodeGenerator()

    return barcodeGenerator.createPartQRcode(partInfoForQRcode)
}

@Composable
fun multipleColorsInText(
    SWelement: MutableState<Boolean>,
    HWelement: MutableState<Boolean>
): AnnotatedString {

    //  /Pxxx/3OSxxx/HWxxx/SWxxx/SNxxx

    var result: AnnotatedString = buildAnnotatedString {
        //Customer
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("/P")
        }

        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("xxx")
        }

        //Article-Index
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("/3OS")
        }

        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("xxx")
        }

        //HW

        if (HWelement.value == true) {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("/HW")
            }

            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("xxx")
            }
        }
        //SW
        if (SWelement.value == true) {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("/SW")
            }

            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("xxx")
            }
        }
        //SN
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("/SN")
        }

        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("xxx")
        }
    }

    return result
}
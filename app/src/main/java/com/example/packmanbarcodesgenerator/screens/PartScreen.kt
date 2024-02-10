package com.example.packmanbarcodesgenerator.screens

import TextAndSwitch
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barcodeGenerator.BarcodeGenerator
import barcodeGenerator.PartQRcode
import com.example.packmanbarcodesgenerator.TypesOfInput
import com.example.packmanbarcodesgenerator.uiElements.TextField_withButtons

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartScreen(
    setFabOnClick: (() -> Unit) -> Unit,
    article: MutableState<String>,
    index: MutableState<String>,
    customerArticle: MutableState<String>,
    HWversion: MutableState<String>,
    SWversion: MutableState<String>,
    serialNumber: MutableState<String>,
    isHWpresent: MutableState<Boolean>,
    isSWpresent: MutableState<Boolean>,
) {

    val configuration = LocalConfiguration.current

//    val switchCheckedStateHWversion = remember { mutableStateOf(false) }
//    val switchCheckedStateSWversion = remember { mutableStateOf(false) }

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
                article.value,
                index.value,
                customerArticle.value,
                HWversion.value,
                SWversion.value,
                serialNumber.value,
                isHWpresent.value,
                isSWpresent.value,
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                PartScreenLandscape(
                    qrCode,
                    article,
                    index,
                    customerArticle,
                    HWversion,
                    SWversion,
                    serialNumber,
                    isHWpresent,
                    isSWpresent
                )
            }

            Configuration.ORIENTATION_PORTRAIT -> {
                PartScreenPortrait(
                    qrCode,
                    article,
                    index,
                    customerArticle,
                    HWversion,
                    SWversion,
                    serialNumber,
                    isHWpresent,
                    isSWpresent
                )
            }

            Configuration.ORIENTATION_UNDEFINED -> {}
        }
    }
}

@Composable
fun PartScreenLandscape(
    qrCode: MutableState<ImageBitmap>,
    article: MutableState<String>,
    index: MutableState<String>,
    customerArticle: MutableState<String>,
    HWversion: MutableState<String>,
    SWversion: MutableState<String>,
    serialNumber: MutableState<String>,
    isHWpresent: MutableState<Boolean>,
    isSWpresent: MutableState<Boolean>
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
                Row {
                    TextAndSwitch(
                        "HW", modifier = Modifier.weight(1f), element = isHWpresent
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    TextAndSwitch(
                        "SW", modifier = Modifier.weight(1f), element = isSWpresent
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        bitmap = qrCode.value,
                        contentDescription = "QR code",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(150.dp)
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
                    labelValue = "артикль",
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = index,
                    modifier = Modifier,
                    labelValue = "індекс",
                    TypesOfInput.integer
                )

                TextField_withButtons(
                    element = customerArticle,
                    modifier = Modifier,
                    labelValue = "Артикль замовника",
                    TypesOfInput.text
                )

                if (isHWpresent.value) {
                    TextField_withButtons(
                        element = HWversion,
                        modifier = Modifier,
                        labelValue = "HW версія",
                        TypesOfInput.text
                    )
                }

                if (isSWpresent.value) {
                    TextField_withButtons(
                        element = SWversion,
                        modifier = Modifier,
                        labelValue = "SW версія",
                        TypesOfInput.text
                    )
                }

                TextField_withButtons(
                    element = serialNumber,
                    modifier = Modifier,
                    labelValue = "Серійний номер",
                    TypesOfInput.text
                )

            }

        }
    }
}

@Composable
fun PartScreenPortrait(
    qrCode: MutableState<ImageBitmap>,
    article: MutableState<String>,
    index: MutableState<String>,
    customerArticle: MutableState<String>,
    HWversion: MutableState<String>,
    SWversion: MutableState<String>,
    serialNumber: MutableState<String>,
    isHWpresent: MutableState<Boolean>,
    isSWpresent: MutableState<Boolean>
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
            TextAndSwitch(
                "HW", modifier = Modifier.weight(1f), element = isHWpresent
            )
            Spacer(modifier = Modifier.width(20.dp))

            TextAndSwitch(
                "SW", modifier = Modifier.weight(1f), element = isSWpresent
            )
        }

        Text(
            text = multipleColorsInText(isSWpresent, isHWpresent),
            Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

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
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom
        ) {
            TextField_withButtons(
                element = article,
                modifier = Modifier,
                labelValue = "артикль",
                TypesOfInput.integer
            )

            TextField_withButtons(
                element = index,
                modifier = Modifier,
                labelValue = "індекс",
                TypesOfInput.integer
            )

            TextField_withButtons(
                element = customerArticle,
                modifier = Modifier,
                labelValue = "Артикль замовника",
                TypesOfInput.text
            )

            if (isHWpresent.value) {
                TextField_withButtons(
                    element = HWversion,
                    modifier = Modifier,
                    labelValue = "HW версія",
                    TypesOfInput.text
                )
            }

            if (isSWpresent.value) {
                TextField_withButtons(
                    element = SWversion,
                    modifier = Modifier,
                    labelValue = "SW версія",
                    TypesOfInput.text
                )
            }

            TextField_withButtons(
                element = serialNumber,
                modifier = Modifier,
                labelValue = "Серійний номер",
                TypesOfInput.text
            )

        }
    }
}

fun generate_PartQRcode(
    article: String,
    index: String,
    customerArticle: String,
    HWversion: String,
    SWversion: String,
    serialNumber: String,
    isHWpresent: Boolean,
    isSWpresent: Boolean
): ImageBitmap {
    val partInfoForQRcode = PartQRcode(
        article = article,
        index = index,
        customerArticle = customerArticle,
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
    SWelement: MutableState<Boolean>, HWelement: MutableState<Boolean>
): AnnotatedString {

    //  /Pxxx/3OSxxx/HWxxx/SWxxx/SNxxx

    val result: AnnotatedString = buildAnnotatedString {
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

        if (HWelement.value) {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("/HW")
            }

            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("xxx")
            }
        }
        //SW
        if (SWelement.value) {
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
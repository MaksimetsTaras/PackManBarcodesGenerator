package com.example.packmanbarcodesgenerator.uiElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.packmanbarcodesgenerator.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nextNumberTool.NextNumberTool

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
        TextField(element.value,
            onValueChange = { text -> element.value = text },
            textStyle = TextStyle(
                fontSize = 20.sp, textAlign = TextAlign.Left, background = Color.Transparent,
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
            modifier = modifier.onFocusChanged {
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
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Green,
                disabledTextColor = Color.Transparent,
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )

        Row() {
            Image(painter = painterResource(id = R.drawable.minus),
                contentDescription = "Minus",
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .width(80.dp)
                    .clickable {
                        val decrementedValue = decrementValue(element.value.text)
                        element.value = TextFieldValue(decrementedValue)
                    })

            Image(painter = painterResource(id = R.drawable.plus),
                contentDescription = "Plus",
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .width(80.dp)
                    .clickable {
                        val incrementedValue = incrementValue(element.value.text)
                        element.value = TextFieldValue(incrementedValue)
                    })
        }
    }
}

fun incrementValue(valueToIncrement: String): String {
    val incrementTool = NextNumberTool()

    return incrementTool.incrementValue(valueToIncrement)
}

fun decrementValue(valueToDecrement: String): String {
    val incrementTool = NextNumberTool()

    return incrementTool.decrementValue(valueToDecrement)
}
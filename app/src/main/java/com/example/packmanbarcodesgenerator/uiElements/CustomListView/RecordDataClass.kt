package com.example.packmanbarcodesgenerator.uiElements.CustomListView

import androidx.compose.runtime.MutableState

data class RecordDataClass(
    var article: String,
    var index: String,
    var customerArticle: String,
    var HWversion: String = "null",
    var SWversion: String = "null",
)
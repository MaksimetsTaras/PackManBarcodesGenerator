package com.example.packmanbarcodesgenerator.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PartScreen() {
    Scaffold() {
        Text(text = "This is Part Screen", modifier = Modifier.padding(24.dp))
        
    }
}
package com.example.packmanbarcodesgenerator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.packmanbarcodesgenerator.screens.MainScreen
import com.example.packmanbarcodesgenerator.ui.theme.PackManBarcodesGeneratorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackManBarcodesGeneratorTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .paint(
                            painterResource(id = R.drawable.background),
                            contentScale = ContentScale.FillBounds
                        ),

                    color = Color.Transparent
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onPause() {
        Toast.makeText(baseContext, "onPause", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onStart() {
        Toast.makeText(baseContext, "onStart", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onRestart() {
        Toast.makeText(baseContext, "onRestart", Toast.LENGTH_SHORT).show()
        super.onRestart()
    }

    override fun onResume() {
        Toast.makeText(baseContext, "onResume", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onDestroy() {
        Toast.makeText(baseContext, "onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onStop() {
        Toast.makeText(baseContext, "onStop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }
}
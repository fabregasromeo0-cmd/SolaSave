package com.example.solasave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.solasave.ui.screens.home.SolaSaveApp
import com.example.solasave.ui.theme.SolaSaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolaSaveTheme {
                SolaSaveApp()
            }
        }
    }
}

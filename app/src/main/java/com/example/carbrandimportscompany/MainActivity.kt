package com.example.carbrandimportscompany

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.carbrandimportscompany.navigation.AppNavHost
import com.example.carbrandimportscompany.ui.theme.CarBrandImportsCompanyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarBrandImportsCompanyTheme {
                AppNavHost() // ✅ Add this line to load your app's navigation
            }
        }
    }
}



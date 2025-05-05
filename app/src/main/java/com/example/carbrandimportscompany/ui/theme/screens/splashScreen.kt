package com.example.carbrandimportscompany.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.carbrandimportscompany.R
import com.example.carbrandimportscompany.ui.theme.screens.login.LoginScreen

@Composable
fun SplashScreen(onNavigationToNext : () -> Unit) {
    val splashScreenDuration = 3000L
    LaunchedEffect(Unit){
        kotlinx.coroutines.delay(splashScreenDuration)
        onNavigationToNext()
    }
    Box(
        modifier = Modifier
            .fillMaxSize() // Ensures Box takes up the entire screen
            .background(Color.Red), // Set background to white
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centers content vertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = "Welcome to Peridah's Saves Company",
                color = Color.Black // Set text color to black for contrast
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {

}



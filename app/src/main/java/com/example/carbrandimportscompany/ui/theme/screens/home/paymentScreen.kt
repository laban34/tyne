package com.example.carbrandimportscompany.ui.theme.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PaymentMethodScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select Payment Method", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        PaymentOptionCard(
            title = "Pay by M-Pesa",
            description = "Use Safaricom M-Pesa for fast and secure payment."
        ) {
            navController.navigate("mpesa_payment")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PaymentOptionCard(
            title = "Pay by Bank",
            description = "Transfer payment directly through your bank account."
        ) {
            navController.navigate("bank_payment")
        }
    }
}

@Composable
fun PaymentOptionCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen(rememberNavController())
}

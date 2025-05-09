//package com.example.carbrandimportscompany.ui.theme.screens.home
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//data class Service(val title: String, val description: String, val contact: String)
//
//@Composable
//fun ServicesScreen(navController: NavController) {
//    val services = listOf(
//        Service("Car Sourcing", "We help you find the perfect car based on your needs.", "0799123456"),
//        Service("Shipping & Clearing", "We handle all shipping and customs paperwork.", "0799765432"),
//        Service("Vehicle Inspection", "We inspect all cars before and after shipping.", "0711122233"),
//        Service("Registration Assistance", "We assist with registration and licensing.", "0722111333"),
//        Service("Financing Help", "We connect you with trusted financial partners.", "0700111222"),
//        Service("Logistics & Delivery", "We deliver your car to your doorstep.", "0744123412")
//    )
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Our Services", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyColumn {
//            items(services) { service ->
//                ServiceCard(service) {
//                    navController.navigate("service_details/${service.title}")
//                }
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ServiceCard(service: Service, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() },
//        elevation = CardDefaults.cardElevation(6.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = service.title, style = MaterialTheme.typography.titleLarge, color = Color.Black)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(text = service.description, style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(6.dp))
//            Text(text = "Contact: ${service.contact}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//        }
//    }
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ServicesScreenPreview() {
//    ServicesScreen(rememberNavController())
//}



//package com.example.carbrandimportscompany.ui.theme.screens.home
//
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun ServicesScreen(navController: NavController) {
//    val services = listOf(
//        Service("Car Sourcing", "We help you find the perfect car based on your needs.", "0799123456"),
//        Service("Shipping & Clearing", "We handle all shipping and customs paperwork.", "0799765432"),
//        Service("Vehicle Inspection", "We inspect all cars before and after shipping.", "0711122233"),
//        Service("Registration Assistance", "We assist with registration and licensing.", "0722111333"),
//        Service("Financing Help", "We connect you with trusted financial partners.", "0700111222"),
//        Service("Logistics & Delivery", "We deliver your car to your doorstep.", "0744123412")
//    )
//
//    Column (modifier = Modifier.padding(16.dp)) {
//        Text("Our Services", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyColumn {
//            items(services) { service ->
//                ServiceCard(service)
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//        }
//    }
//}
//
//data class Service(val title: String, val description: String, val contact: String)
//
//@Composable
//fun ServiceCard(service: Service) {
//    val context = LocalContext.current
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                val intent = Intent(Intent.ACTION_DIAL).apply {
//                    data = Uri.parse("tel:${service.contact}")
//                }
//                context.startActivity(intent)
//            },
//        elevation = CardDefaults.cardElevation(6.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = service.title, style = MaterialTheme.typography.titleLarge, color = Color.Black)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(text = service.description, style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(6.dp))
//            Text(text = "Contact: ${service.contact}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//        }
//    }
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ServicesScreenPreview() {
//    ServicesScreen(rememberNavController())
//}

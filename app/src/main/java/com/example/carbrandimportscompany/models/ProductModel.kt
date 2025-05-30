package com.example.carbrandimportscompany.models

data class CarModel(
    var brandName: String = "",     // e.g., Toyota, BMW
    var model: String = "",         // e.g., Corolla, X5
    var modelYear: String = "",     // e.g., 2020
    var description: String = "",   // Optional notes about the car
    var imageUrl: String = "",      // URL to the uploaded image
    var carId: String = ""          // Unique identifier
)
data class ServiceProvider(
    val name: String,
    val contact: String,
    val role: String
)

data class Service(
    val title: String,
    val description: String,
    val providers: List<ServiceProvider>
)

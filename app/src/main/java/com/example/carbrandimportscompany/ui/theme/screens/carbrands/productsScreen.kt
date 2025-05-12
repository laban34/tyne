package com.example.carbrandimportscompany.ui.theme.screens.carbrands

import android.net.Uri
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carbrandimportscompany.R
// import androidx.compose.ui.text.input.KeyboardOptions // Uncomment if using keyboard options

@Composable
fun ProductScreen(navController: NavController) {
    var productName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    // Image picker
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Spare Part", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price (KES)") },
            // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Product Image", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                val inputStream = imageUri?.let { context.contentResolver.openInputStream(it) }
                val bitmap = inputStream?.use { BitmapFactory.decodeStream(it) }
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Placeholder",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // TODO: Upload product + image to Firebase
                // After successful upload, navigate back or to another screen
                navController.navigate("product_list") {
                    popUpTo("add_product") { inclusive = true } // Optional cleanup
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Product")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddProductScreenPreview() {
    ProductScreen(rememberNavController())
}






//package com.example.carbrandimportscompany.ui.theme.screens.carbrands
//
//import android.net.Uri
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.foundation.clickable
//import androidx.compose.ui.res.painterResource
//import com.example.carbrandimportscompany.R
//import android.graphics.BitmapFactory
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.ui.text.input.KeyboardType
////import androidx.compose.ui.text.input.KeyboardOptions
//
//
//
//
//@Composable
//fun AddProductScreen(navController: NavController) {
//    var productName by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    val context = LocalContext.current
//
//    // Image picker
//    val imagePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        imageUri = uri
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("Add Spare Part", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = productName,
//            onValueChange = { productName = it },
//            label = { Text("Product Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = description,
//            onValueChange = { description = it },
//            label = { Text("Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = price,
//            onValueChange = { price = it },
//            label = { Text("Price (KES)") },
////            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = category,
//            onValueChange = { category = it },
//            label = { Text("Category") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Image preview and select button
//        Text("Product Image", style = MaterialTheme.typography.titleMedium)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp)
//                .clickable { imagePickerLauncher.launch("image/*") },
//            contentAlignment = Alignment.Center
//        ) {
//            if (imageUri != null) {
//                val inputStream = imageUri?.let { context.contentResolver.openInputStream(it) }
//                val bitmap = inputStream?.use { BitmapFactory.decodeStream(it) }
//                bitmap?.let {
//                    Image(
//                        bitmap = it.asImageBitmap(),
//                        contentDescription = "Selected Image",
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//            } else {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_person),
//                    contentDescription = "Placeholder",
//                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
//                    modifier = Modifier.size(64.dp)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(
//            onClick = {
//                // TODO: Upload product + image logic
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Upload Product")
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AddProductScreenPreview() {
//    AddProductScreen(rememberNavController())
//}

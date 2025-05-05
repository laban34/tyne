package com.example.carbrandimportscompany.ui.theme.screens.carbrands

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.carbrandimportscompany.R
import com.example.carbrandimportscompany.data.CarViewModel
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun AddCarScreen(navController: NavController) {
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    var brandName by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var modelYear by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current
    val carViewModel: CarViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Car",
            fontSize = 30.sp,
            color = Color.Yellow,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Gray)
                .padding(20.dp)
                .fillMaxWidth()
        )

        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        ) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clickable { launcher.launch("image/*") }
            )
        }

        Text(text = "Attach car image")

        OutlinedTextField(
            value = brandName,
            onValueChange = { brandName = it },
            label = { Text("Brand Name") },
            placeholder = { Text("e.g., Toyota, BMW") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Model") },
            placeholder = { Text("e.g., Corolla, X5") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = modelYear,
            onValueChange = { modelYear = it },
            label = { Text("Model Year") },
            placeholder = { Text("e.g., 2022") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            placeholder = { Text("Additional details") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            singleLine = false
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // TODO: Implement Home navigation
                },
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text("HOME")
            }

            Button(onClick = {
                imageUri.value?.let {
                    carViewModel.uploadCarWithImage(
                        imageUri = it,
                        context = context,
                        brandName = brandName,
                        model = model,
                        modelYear = modelYear,
                        description = description,
                        navController = navController
                    )
                } ?: Toast.makeText(context, "Please pick an image", Toast.LENGTH_LONG).show()
            }) {
                Text("SAVE")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCarScreenPreview() {
    AddCarScreen(rememberNavController())
}




//package com.example.carbrandimportscompany.ui.theme.screens.carbrands
//
//import android.net.Uri
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
//import com.example.carbrandimportscompany.R
//import com.example.carbrandimportscompany.data.CarViewModel
//
//@Composable
//fun AddProductScreen(navController: NavController) {
//    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let { imageUri.value = it }
//    }
//
//    var productname by remember { mutableStateOf("") }
//    var productquantity by remember { mutableStateOf("") }
//    var productprice by remember { mutableStateOf("") }
//    var productbrand by remember { mutableStateOf("") }
//    var desc by remember { mutableStateOf("") }
//
//    val productViewModel: CarViewModel = viewModel()
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Add new product",
//            fontSize = 30.sp,
//            color = Color.Yellow,
//            fontFamily = FontFamily.SansSerif,
//            fontStyle = FontStyle.Normal,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .background(Color.Gray)
//                .padding(20.dp)
//                .fillMaxWidth()
//        )
//
//        Card(
//            shape = CircleShape,
//            modifier = Modifier
//                .padding(10.dp)
//                .size(200.dp)
//        ) {
//            AsyncImage(
//                model = imageUri.value ?: R.drawable.ic_person,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(200.dp)
//                    .clickable { launcher.launch("image/*") }
//            )
//        }
//
//        Text(text = "Attach product image")
//
//        OutlinedTextField(
//            value = productname,
//            onValueChange = { productname = it },
//            label = { Text("Product Name") },
//            placeholder = { Text("Enter the product name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = productquantity,
//            onValueChange = { productquantity = it },
//            label = { Text("Product Quantity") },
//            placeholder = { Text("Enter product quantity") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = productprice,
//            onValueChange = { productprice = it },
//            label = { Text("Product Price") },
//            placeholder = { Text("Enter product price") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = productbrand,
//            onValueChange = { newBrand -> productbrand = newBrand },
//            label = { Text(text = "Product brand") },
//            placeholder = { Text(text = "Enter product brand") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = desc,
//            onValueChange = { desc = it },
//            label = { Text("Brief Description") },
//            placeholder = { Text("Enter product description") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp),
//            singleLine = false
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(
//                onClick = {
//                    // TODO: Navigate to home
//                },
//                colors = ButtonDefaults.buttonColors(Color.DarkGray)
//            ) {
//                Text("HOME")
//            }
//
//            Button(onClick = {
//                imageUri.value?.let {
//                    CarViewModel.uploadCarWithImage(it, context, desc:String)
//                } ?: Toast.makeText(context, "Please pick an image", Toast.LENGTH_LONG).show()
//            } )  {
//                Text(text = "SAVE")
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AddProductScreenPreview() {
//    AddProductScreen(rememberNavController())
//}

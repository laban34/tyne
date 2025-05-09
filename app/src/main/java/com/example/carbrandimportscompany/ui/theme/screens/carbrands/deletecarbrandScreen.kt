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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.carbrandimportscompany.data.CarViewModel
import com.example.carbrandimportscompany.models.CarModel
import com.example.carbrandimportscompany.R
import com.google.firebase.database.*

@Composable
fun UpdateCarScreen(navController: NavController, carId: String) {
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    var brandName by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var modelYear by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val carViewModel: CarViewModel = viewModel()
    val context = LocalContext.current
    val carRef = FirebaseDatabase.getInstance().getReference("Cars/$carId")

    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val car = snapshot.getValue(CarModel::class.java)
                car?.let {
                    brandName = it.brandName       // Car brand, e.g., Toyota
                    model = it.model               // Car model, e.g., Corolla
                    modelYear = it.modelYear       // Year of manufacture, e.g., 2022
                    description = it.description   // Car description/details

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        }

        carRef.addValueEventListener(listener)
        onDispose { carRef.removeEventListener(listener) }
    }

    Column(
        modifier = Modifier.padding(10.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().background(Color.Red).padding(16.dp)
        ) {
            Text(
                text = "UPDATE CAR",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Card(shape = CircleShape, modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable { launcher.launch("image/*") }
            )
        }

        Text(text = "Upload Car Image Here")

        OutlinedTextField(
            value = brandName,
            onValueChange = { brandName = it },
            label = { Text("Enter car brand name") },
            placeholder = { Text("E.g. Toyota, BMW") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Enter car model") },
            placeholder = { Text("E.g. Corolla, X5") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = modelYear,
            onValueChange = { modelYear = it },
            label = { Text("Enter model year") },
            placeholder = { Text("E.g. 2022") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Enter description") },
            placeholder = { Text("Provide car details") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO: Handle Go Back*/ }) {
//                Text(text = "GO BACK")
//            }
//            Button(onClick = { navController.popBackStack() }) {
//                Text("GO BACK")
//            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = {
                val carViewModel = CarViewModel()
                carViewModel.updateCar(
                    context = context,
                    navController = navController,
                    brandName = brandName,
                    model = model,
                    modelYear = modelYear,
                    description = description,
                    carId = carId
                )
            }) {
                Text(text = "Update Car")
            }
                Text("UPDATE")
           }
        }
    }
}



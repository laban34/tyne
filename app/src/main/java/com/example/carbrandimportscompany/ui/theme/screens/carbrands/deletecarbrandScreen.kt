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
            Button(onClick = { navController.popBackStack() }) {
                Text("GO BACK")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = {
                val carRepository = CarViewModel()
                carRepository.updateStudent( // You should rename this to `updateCar` in your ViewModel
                    context = context,
                    navController = navController,
                    name = brandName,
                    gender = model,
                    course = modelYear,
                    desc = description,
                    studentId = carId // rename this param to `carId` in your ViewModel
                )
            }) {
                Text("UPDATE")
            }
        }
    }
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
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
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
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
//import com.example.carbrandimportscompany.data.CarViewModel
//import com.example.carbrandimportscompany.models.CarModel
//import com.example.carbrandimportscompany.R
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//@Composable
//fun UpdateCarScreen(navController: NavController,studentId: String){
//    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let { imageUri.value=it } }
//    var name by remember { mutableStateOf("") }
//    var gender by remember { mutableStateOf("") }
//    var course by remember { mutableStateOf("") }
//    var desc by remember { mutableStateOf("") }
//    val studentViewModel: CarViewModel= viewModel()
//    val context= LocalContext.current
//    val currentDataRef  = FirebaseDatabase.getInstance()
//        .getReference().child("Students/$studentId")
//
//    DisposableEffect(Unit){
//        val listener = object  : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val student = snapshot.getValue(CarModel::class.java)
//                student?.let {
//                    name = it.name
//                    gender = it.gender
//                    course = it.course
//                    desc = it.desc
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
//            }
//        }
//        currentDataRef.addValueEventListener(listener)
//        onDispose { currentDataRef.removeEventListener(listener) }
//    }
//    Column (modifier = Modifier.padding(10.dp)
//        .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally){
//        Box(modifier = Modifier.fillMaxWidth()
//            .background(Color.Red).padding(16.dp)){ Text(text = "UPDATE STUDENT",
//            fontStyle = FontStyle.Normal,
//            fontWeight = FontWeight.Bold,
//            fontSize = 26.sp,
//            textAlign = TextAlign.Center,
//            color = Color.White,
//            modifier = Modifier.fillMaxWidth()) }
//        Card(shape = CircleShape, modifier = Modifier.padding(10.dp).size(200.dp)) {
//            AsyncImage(model = imageUri.value ?: R.drawable.ic_person,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.size(200.dp)
//                    .clickable { launcher.launch("image/*") })
//        }
//        Text(text = "Upload Picture Here")
//
//        OutlinedTextField(value = name,
//            onValueChange = {newName->name=newName},
//            label = { Text(text = "Enter student name") },
//            placeholder = { Text(text = "PLease enter student name") },
//            modifier = Modifier.fillMaxWidth())
//        OutlinedTextField(value = gender,
//            onValueChange = {newGender->gender=newGender},
//            label = { Text(text = "Enter student gender") },
//            placeholder = { Text(text = "PLease enter student gender") },
//            modifier = Modifier.fillMaxWidth())
//        OutlinedTextField(value = course,
//            onValueChange = {newCourse->course=newCourse},
//            label = { Text(text = "Enter student course") },
//            placeholder = { Text(text = "PLease enter student course") },
//            modifier = Modifier.fillMaxWidth())
//        OutlinedTextField(value = desc,
//            onValueChange = {newDesc->desc=newDesc},
//            label = { Text(text = "Enter student description") },
//            placeholder = { Text(text = "PLease enter student description") },
//            modifier = Modifier.fillMaxWidth().height(150.dp), singleLine = false)
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(onClick = { /*TODO: Handle Go Back*/ }) {
//                Text(text = "GO BACK")
//            }
//            Spacer(modifier = Modifier.width(20.dp))
//            Button(onClick = {
//                val studentRepository = CarViewModel()
//                studentRepository.updateStudent(
//                    context = context,
//                    navController = navController,
//                    name = name,
//                    gender = gender,
//                    course = course,
//                    desc = desc,
//                    studentId = studentId
//                )
//            }) {
//                Text(text = "UPDATE")
//            }
//        }
//    }
//}

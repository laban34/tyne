package com.example.carbrandimportscompany.ui.theme.screens.carbrands

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.carbrandimportscompany.data.CarViewModel
import com.example.carbrandimportscompany.models.CarModel
import com.example.carbrandimportscompany.navigation.ROUTE_UPDATE_PRODUCT

@Composable
fun ViewCarBrandsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val carViewModel = CarViewModel()
    val emptyCarState = remember { mutableStateOf(CarModel("", "", "", "", "", "")) }
    val carBrandList = remember { mutableStateListOf<CarModel>() }
    val cars = carViewModel.fetchCars(emptyCarState, carBrandList, context)


//    val cars = carViewModel.viewModelScope(emptyCarState, carBrandList, context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "All Car Brands",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(cars.size) { index ->
                CarBrandItem(
                    brand = cars[index].brandName,
                    model = cars[index].model,
                    origin = cars[index].modelYear,
                    description = cars[index].description,
                    carId = cars[index].carId,
                    imageUrl = cars[index].imageUrl,
                    navController = navController,
                    carViewModel = carViewModel
                )
            }
        }

    }
}

@Composable
fun CarBrandItem(
    brand: String,
    model: String,
    origin: String,
    description: String,
    carId: String,
    imageUrl: String,
    navController: NavHostController,
    carViewModel: CarViewModel
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(220.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Column {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                        .height(150.dp)
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = {
                            carViewModel.deleteCar(context, carId, navController)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text("REMOVE", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            navController.navigate("$ROUTE_UPDATE_PRODUCT/$carId")
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text("UPDATE", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                LabeledValue(label = "Brand", value = brand)
                LabeledValue(label = "Model", value = model)
                LabeledValue(label = "Origin", value = origin)
                LabeledValue(label = "Description", value = description)
            }
        }
    }
}

@Composable
fun LabeledValue(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = label, color = Color.LightGray, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun ViewCarBrandsPreview() {
    ViewCarBrandsScreen(rememberNavController())
}



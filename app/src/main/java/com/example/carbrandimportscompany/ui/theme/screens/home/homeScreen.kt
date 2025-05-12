
package com.example.carbrandimportscompany.ui.theme.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carbrandimportscompany.R
import com.example.carbrandimportscompany.navigation.ROUTE_ADD_PRODUCT
import com.example.carbrandimportscompany.navigation.ROUTE_PAYMENTS
import com.example.carbrandimportscompany.navigation.ROUTE_PRODUCTS
import com.example.carbrandimportscompany.navigation.ROUTE_SERVICES
import com.example.carbrandimportscompany.navigation.ROUTE_VIEW_CARS
//import com.example.carbrandimportscompany.navigation.ROUTE_CAR_BRANDS
//import com.example.carbrandimportscompany.navigation.ROUTE_ORDERS
//import com.example.carbrandimportscompany.navigation.ROUTE_PAYMENTS
//import com.example.carbrandimportscompany.navigation.ROUTE_SETTINGS
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val selectedItem = remember { mutableStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.Transparent) {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = {
                        selectedItem.value = 0
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:0716538474")
                        }
                        context.startActivity(intent)
                    },
                    icon = { Icon(Icons.Filled.Phone, contentDescription = "Phone") },
                    label = { Text(text = "Phone") },
                )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = {
                        selectedItem.value = 1
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:info@emobilis.ac.ke")
                            putExtra(Intent.EXTRA_SUBJECT, "inquiry")
                            putExtra(Intent.EXTRA_TEXT, "hello how do i join your school?")
                        }
                        context.startActivity(intent)
                    },
                    icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    label = { Text(text = "Email") },
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = {
                        selectedItem.value = 2
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Download app here :https://www.download.com")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    },
                    icon = { Icon(Icons.Filled.Share, contentDescription = "Share") },
                    label = { Text(text = "Share") },
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Dashboard background image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CenterAlignedTopAppBar(
                    title = { Text(text = " Order yours now", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { /* Home click logic */ }) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Search click logic */ }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.White)
                        }
                        IconButton(onClick = { /* Profile click logic */ }) {
                            Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                        }
                        Button(onClick = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("login") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                        }) {
                            Text("Logout")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


//                Row(modifier = Modifier.wrapContentWidth()) {
//                    DashboardCard("Car Brands", navController, route = ROUTE_CAR_BRANDS)
//                    DashboardCard("Orders", navController, route = ROUTE_ORDERS)
//                    DashboardCard("Payments", navController, route = ROUTE_PAYMENTS)
//                    DashboardCard("Offers", navController) // No route – static card
//                    DashboardCard("Settings", navController, route = ROUTE_SETTINGS)
//                    DashboardCard("Services", navController) // No route – static card
//                }



//                Row(modifier = Modifier.wrapContentWidth()) {
//                    DashboardCard("Car Brands", navController)
//                    DashboardCard("Orders", navController)
//                    DashboardCard("Payments", navController)
//                    DashboardCard("Offers", navController)
//                    DashboardCard("Settings", navController)
//                    DashboardCard("Services", navController)
//                }


                Row {
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate(ROUTE_ADD_PRODUCT)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Car Brands",
                                color = Color.White
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate(ROUTE_VIEW_CARS)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Oders",
                                color = Color.White
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate(ROUTE_PAYMENTS)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Payment",
                                color = Color.White
                            )
                        }
                    }
                }

                Row {
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate(ROUTE_ADD_PRODUCT)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "cars",
                                color = Color.White
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate(ROUTE_PRODUCTS)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Products",
                                color = Color.White
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.padding(10.dp) .clickable {
                            navController.navigate(ROUTE_SERVICES)},
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(Color.Blue)
                    )
                    {
                        Box(
                            modifier = Modifier.height(100.dp)
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Services",
                                color = Color.White
                            )
                        }
                    }
                }

            }
        }
    }
}


//@Composable
//fun DashboardCard(
//    title: String,
//    navController: NavController,
//    route: String? = null // Make route optional
//) {
//    val modifier = if (route != null) {
//        Modifier
//            .padding(10.dp)
//            .clickable {
//                navController.navigate(route)
//            }
//    } else {
//        Modifier.padding(10.dp) // No clickable modifier
//    }
//
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(20.dp),
//        elevation = CardDefaults.cardElevation(10.dp),
//        colors = CardDefaults.cardColors(Color.Blue)
//    ) {
//        Box(
//            modifier = Modifier
//                .height(80.dp)
//                .padding(25.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = title, color = Color.White)
//        }
//    }


//        Box(
//            modifier = Modifier
//                .height(80.dp)
//                .padding(25.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = title, color = Color.White)
//        }
//    }

//@Composable
//fun DashboardCard(title: String, navController: NavController) {
//    Card(
//        modifier = Modifier
//            .padding(10.dp)
//            .clickable {
//                navController.navigate(ROUTE_ADD_PRODUCT)
//            },
//        shape = RoundedCornerShape(20.dp),
//        elevation = CardDefaults.cardElevation(10.dp),
//        colors = CardDefaults.cardColors(Color.Blue)
//    ) {
//        Box(
//            modifier = Modifier
//                .height(80.dp)
//                .padding(25.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = title)
//        }
//    }
//}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
}



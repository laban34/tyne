package com.example.carbrandimportscompany.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carbrandimportscompany.R
import com.example.carbrandimportscompany.data.AuthViewModel
import com.example.carbrandimportscompany.navigation.ROUTE_LOGIN


@Composable
fun RegisterScreen(navController: NavController){


    var authViewModel: AuthViewModel = viewModel()

    var firstname by remember { mutableStateOf(value = "") }
    var lastname by remember { mutableStateOf(value = "") }
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    val context = LocalContext.current
    val passwordVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Text(text = "Register Here!!",
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Gray).fillMaxWidth().padding(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.wrapContentHeight().fillMaxWidth().height(80.dp))
        OutlinedTextField(
            value = firstname,

            onValueChange = {newFirstName -> firstname = newFirstName},
            label = { Text(text = "Enter first name") },
            placeholder = { Text(text = "Please enter first name") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") })
        OutlinedTextField(value = lastname,

            onValueChange = {newLastName->lastname=newLastName}, label = { Text(text = "Enter Last Name") }, placeholder = { Text(text = "Please Enter Last Name") }, modifier = Modifier.wrapContentWidth().align(
                Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") })
        OutlinedTextField(value = email,

            onValueChange = {newEmail->email=newEmail}, label = { Text(text = "Enter Email") }, placeholder = { Text(text = "Please enter email") }, modifier = Modifier.wrapContentWidth().align(
                Alignment.CenterHorizontally), leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") })
        OutlinedTextField(
            value = password,


            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Enter Password") },
            placeholder = { Text(text = "Please enter Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") })
        Button(onClick = {
            authViewModel.signup(firstname, lastname, email, password, navController, context)
        }, modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(
            Color.Black)) { Text(text = "Register") }
        Text(text = buildAnnotatedString { append("If already registered, Login here") }, modifier = Modifier.wrapContentWidth().align(
            Alignment.CenterHorizontally).clickable {
            navController.navigate(ROUTE_LOGIN)
        })

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}

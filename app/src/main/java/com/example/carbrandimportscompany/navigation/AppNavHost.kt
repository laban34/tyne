

package com.example.carbrandimportscompany.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carbrandimportscompany.ui.theme.screens.SplashScreen
import com.example.carbrandimportscompany.ui.theme.screens.carbrands.AddCarScreen
import com.example.carbrandimportscompany.ui.theme.screens.carbrands.UpdateCarScreen

import com.example.carbrandimportscompany.ui.theme.screens.carbrands.ViewCarBrandsScreen
import com.example.carbrandimportscompany.ui.theme.screens.home.DashboardScreen


import com.example.carbrandimportscompany.ui.theme.screens.login.LoginScreen
import com.example.carbrandimportscompany.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH){
    NavHost(navController=navController,startDestination=startDestination) {
        composable(ROUTE_SPLASH){ SplashScreen {
            navController.navigate(ROUTE_REGISTER){
                popUpTo(ROUTE_SPLASH){inclusive=true}} }
        }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN){ LoginScreen(navController) }
        composable(ROUTE_HOME){ DashboardScreen(navController) }
        composable(ROUTE_ADD_PRODUCT){ AddCarScreen(navController) }
        composable(ROUTE_VIEW_CARS){ ViewCarBrandsScreen( navController) }


//        composable("car_brands") { CarBrandsScreen(navController) }
//        composable("orders") { OrdersScreen(navController) }
//        composable("payments") { PaymentsScreen(navController) }
//        composable("settings") { SettingsScreen(navController) }
//

        composable("$ROUTE_UPDATE_PRODUCT/{carId}") {
                passedData -> UpdateCarScreen(
            navController, passedData.arguments?.getString("carId")!! )
        }

    }
}
